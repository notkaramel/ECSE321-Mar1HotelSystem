package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.AssignmentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.BookingRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RequestRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.AssignmentRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.AssignmentResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.BookingRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.BookingResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.EmployeeDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.GeneralUserDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.PaymentRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.PaymentResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RequestRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RequestResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomResponseDto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssignmentIntegrationTest {

    @Autowired
    private TestRestTemplate assignmentClient;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private GeneralUserRepository generalUserRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    // Clear database before and after each test
    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        assignmentRepository.deleteAll();
        requestRepository.deleteAll();
        bookingRepository.deleteAll();
        employeeRepository.deleteAll();
        paymentRepository.deleteAll();
        roomRepository.deleteAll();
        // hotelRepository.deleteAll();
        // hotelScheduleRepository.deleteAll();
        // customHoursRepository.deleteAll();
        // operatingHoursRepository.deleteAll();
        generalUserRepository.deleteAll();
    }

    // Create assignee
    private String createAssignee(String uniqueEmail) {
        EmployeeDto employeeDto = new EmployeeDto("Funny", "Dude", uniqueEmail, 1234567890, "password", 40);
        ResponseEntity<EmployeeDto> response = assignmentClient.postForEntity("/employee", employeeDto,
                EmployeeDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getEmail();

    }

    // Create payment
    private int createPayment() {
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(100);
        ResponseEntity<PaymentResponseDto> response = assignmentClient.postForEntity("/payment/create",
                paymentRequestDto, PaymentResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getPaymentId();
    }

    // Create general user
    private String createGeneralUser() {
        GeneralUserDto generalUserDto = new GeneralUserDto("Funny", "Dude", "dude@funny.com", 1234567890);
        ResponseEntity<GeneralUserDto> response = assignmentClient.postForEntity("/generalUsers/create", generalUserDto,
                GeneralUserDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getEmail();
    }

    // Create room
    private int createRoom() {
        RoomRequestDto roomRequestDto = new RoomRequestDto(RoomType.Deluxe, BedType.Queen, true, 100, 2);
        ResponseEntity<RoomResponseDto> response = assignmentClient.postForEntity("/room/create", roomRequestDto,
                RoomResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getRoomId();
    }

    // Create booking
    private int createBooking() {
        int paymentId = createPayment();
        String generalUserId = createGeneralUser();
        int roomId = createRoom();
        BookingRequestDto bookingRequestDto = new BookingRequestDto(generalUserId, roomId, paymentId);
        ResponseEntity<BookingResponseDto> response = assignmentClient.postForEntity("/booking/create",
                bookingRequestDto, BookingResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getBookingId();
    }

    private int createRequest() {
        int bookingId = createBooking();
        RequestRequestDto requestRequestDto = new RequestRequestDto("I need a towel", bookingId, false);
        ResponseEntity<RequestResponseDto> response = assignmentClient.postForEntity("/request/create",
                requestRequestDto, RequestResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getRequestId();
    }

    private int createAssignment(String assigneeId, int requestId) {
        AssignmentRequestDto assignmentRequestDto = new AssignmentRequestDto(assigneeId, requestId);
        ResponseEntity<AssignmentResponseDto> response = assignmentClient.postForEntity("/assignment/create",
                assignmentRequestDto, AssignmentResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getAssignmentId();
    }

    @Test
    public void testCreateAssignment() {

        String assigneeId = createAssignee("funny@hello.com");
        int requestId = createRequest();
        int assignmentId = createAssignment(assigneeId, requestId);

        assertNotNull(assignmentId);
        assertEquals(1, assignmentRepository.count());
    }

    @Test
    public void testGetAssignment() {

        String assigneeId = createAssignee("funny@hello.com");
        int requestId = createRequest();
        int assignmentId = createAssignment(assigneeId, requestId);

        ResponseEntity<AssignmentResponseDto> response = assignmentClient.getForEntity("/assignments/" + assignmentId,
                AssignmentResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignmentId, response.getBody().getAssignmentId());
        assertEquals(assigneeId, response.getBody().getEmployee().getEmail());
        assertEquals(requestId, response.getBody().getRequest().getRequestId());

    }

    @Test
    public void testUpdateAssignment() {
        String assigneeId = createAssignee("funny@hello.com");
        String newAssigneeId = createAssignee("EvanMoore@Funnie.com");
        int requestId = createRequest();
        int assignmentId = createAssignment(assigneeId, requestId);

        AssignmentRequestDto assignmentRequestDto = new AssignmentRequestDto(newAssigneeId, requestId);
        assignmentClient.put("/assignments/update/" + assignmentId, assignmentRequestDto);

        AssignmentResponseDto assignment = assignmentClient.getForObject("/assignments/" + assignmentId,
                AssignmentResponseDto.class);
        assertNotNull(assignment);
        assertEquals(assignmentId, assignment.getAssignmentId());
        assertEquals(assigneeId, assignment.getEmployee().getEmail());
        assertEquals(requestId, assignment.getRequest().getRequestId());

    }

    @Test
    public void testDeleteAssignment() {
        String assigneeId = createAssignee("EvanMoore@Funnie.com");
        int requestId = createRequest();
        int assignmentId = createAssignment(assigneeId, requestId);

        assignmentClient.delete("/assignments/delete/" + assignmentId);

        assertEquals(0, assignmentRepository.count());
    }

    @Test
    public void testGetAllAssignments() {

        String assigneeId = createAssignee("EvanMoore@Funnie.com");
        int requestId = createRequest();
        int assignmentId = createAssignment(assigneeId, requestId);
        // It's a bit cursed but it works :) Otherwise you can create a simple
        // MultipleAssignmentResponseDto
        ResponseEntity<List<AssignmentResponseDto>> response = assignmentClient.exchange("/assignments/all",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<AssignmentResponseDto>>() {
                });
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AssignmentResponseDto> assignmentResponseDtoList = response.getBody();
        assertNotNull(assignmentResponseDtoList);
        assertEquals(1, assignmentResponseDtoList.size());
        assertEquals(assignmentId, assignmentResponseDtoList.get(0).getAssignmentId());
        assertEquals(assigneeId, assignmentResponseDtoList.get(0).getEmployee().getEmail());
        assertEquals(requestId, assignmentResponseDtoList.get(0).getRequest().getRequestId());

    }

}
