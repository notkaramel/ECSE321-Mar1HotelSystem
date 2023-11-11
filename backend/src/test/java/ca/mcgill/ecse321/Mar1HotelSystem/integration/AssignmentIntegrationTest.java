package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public void clearDatabase() {
        requestRepository.deleteAll();
        bookingRepository.deleteAll();
        paymentRepository.deleteAll();
        roomRepository.deleteAll();
        employeeRepository.deleteAll();
        generalUserRepository.deleteAll();
        assignmentRepository.deleteAll();
    }

    // Create assignee
    private String createAssignee() {

        EmployeeDto employeeDto = new EmployeeDto("Funny", "Dude", "funnyude@hahaha.ca", 1234567890, "password", 40);
        ResponseEntity<EmployeeDto> response = assignmentClient.postForEntity("/employee", employeeDto, EmployeeDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getEmail();
    
    }


    // Create payment
    private int createPayment() {
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(100);
        ResponseEntity<PaymentResponseDto> response = assignmentClient.postForEntity("/payment/create", paymentRequestDto, PaymentResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getPaymentId();
    }


    // Create general user
    private String createGeneralUser() {
        GeneralUserDto generalUserDto = new GeneralUserDto("Funny", "Dude", "dude@funny.com", 1234567890);
        ResponseEntity<GeneralUserDto> response = assignmentClient.postForEntity("/generalUsers/create", generalUserDto, GeneralUserDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getEmail();
    }


    // Create room
    private int createRoom() {
        RoomRequestDto roomRequestDto = new RoomRequestDto(RoomType.Deluxe, BedType.Queen, true, 100, 2);
        ResponseEntity<RoomResponseDto> response = assignmentClient.postForEntity("/room/create", roomRequestDto, RoomResponseDto.class);
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
        ResponseEntity<BookingResponseDto> response = assignmentClient.postForEntity("/booking/create", bookingRequestDto, BookingResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getBookingId();
    }



    private int createRequest() {
        int bookingId = createBooking();
        RequestRequestDto requestRequestDto = new RequestRequestDto("I need a towel", bookingId, false); 
        ResponseEntity<RequestResponseDto> response = assignmentClient.postForEntity("/request/create", requestRequestDto, RequestResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getRequestId();
    }

    private int createAssignment(String assigneeId, int requestId) {
        AssignmentRequestDto assignmentRequestDto = new AssignmentRequestDto(assigneeId, requestId);
        ResponseEntity<AssignmentResponseDto> response = assignmentClient.postForEntity("/assignment/create", assignmentRequestDto, AssignmentResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getAssignmentId();
    }

    @Test
    public void testCreateAssignment() {

        String assigneeId = createAssignee();
        int requestId = createRequest();
        int assignmentId = createAssignment(assigneeId, requestId);

        assertNotNull(assignmentId);
        assertEquals(1, assignmentRepository.count());
    }


    @Test
    public void testGetAssignment() {

        String assigneeId = createAssignee();
        int requestId = createRequest();
        int assignmentId = createAssignment(assigneeId, requestId);

        ResponseEntity<AssignmentResponseDto> response = assignmentClient.getForEntity("/assignment/" + assignmentId, AssignmentResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignmentId, response.getBody().getAssignmentId());
        assertEquals(assigneeId, response.getBody().getEmployee().getEmail());
        assertEquals(requestId, response.getBody().getRequest().getRequestId());

    }

    @Test
    public void testUpdateAssignment() {

        String assigneeId = createAssignee();
        int requestId = createRequest();
        int assignmentId = createAssignment(assigneeId, requestId);

        AssignmentRequestDto assignmentRequestDto = new AssignmentRequestDto(assigneeId, requestId);
        ResponseEntity<AssignmentResponseDto> response = assignmentClient.postForEntity("/assignment/update/" + assignmentId, assignmentRequestDto, AssignmentResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignmentId, response.getBody().getAssignmentId());
        assertEquals(assigneeId, response.getBody().getEmployee().getEmail());
        assertEquals(requestId, response.getBody().getRequest().getRequestId());

    }

    @Test
    public void testDeleteAssignment() {

        String assigneeId = createAssignee();
        int requestId = createRequest();
        int assignmentId = createAssignment(assigneeId, requestId);

        ResponseEntity<Response> response = assignmentClient.getForEntity("/assignment/delete/" + assignmentId, Response.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, assignmentRepository.count());

    }


    @Test
    public void testGetAllAssignments() {

        String assigneeId = createAssignee();
        int requestId = createRequest();
        int assignmentId = createAssignment(assigneeId, requestId);

        ResponseEntity<AssignmentResponseDto[]> response = assignmentClient.getForEntity("/assignment/all", AssignmentResponseDto[].class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().length);
        assertEquals(assignmentId, response.getBody()[0].getAssignmentId());
        assertEquals(assigneeId, response.getBody()[0].getEmployee().getEmail());
        assertEquals(requestId, response.getBody()[0].getRequest().getRequestId());

    }

    
}
