package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.BookingRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RequestRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.BookingRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.BookingResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.GeneralUserDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.PaymentRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.PaymentResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RequestRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RequestResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestIntegrationTest {
    @Autowired
    private TestRestTemplate requestClient;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private GeneralUserRepository generalUserRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        requestRepository.deleteAll();
        bookingRepository.deleteAll();
        roomRepository.deleteAll();
        generalUserRepository.deleteAll();
        paymentRepository.deleteAll();
    }

    private int createDemoRoom() {
        RoomRequestDto roomRequestDto = new RoomRequestDto(Room.RoomType.Deluxe, Room.BedType.King, true, 200, 4);
        ResponseEntity<RoomResponseDto> response = requestClient.postForEntity("/room/create", roomRequestDto,
                RoomResponseDto.class);
        assertNotNull(response);
        RoomResponseDto room = response.getBody();
        assertNotNull(room);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(Room.RoomType.Deluxe, room.getRoomType());
        return room.getRoomId();
    }

    private int createDemoPayment() {
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(100);
        ResponseEntity<PaymentResponseDto> response = requestClient.postForEntity("/payment/create", paymentRequestDto,
                PaymentResponseDto.class);
        assertNotNull(response);
        PaymentResponseDto payment = response.getBody();
        assertNotNull(payment);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(100, payment.getAmount());
        return payment.getPaymentId();
    }

    private String createDemoGeneralUser() {
        GeneralUserDto generalUserDto = new GeneralUserDto("John", "Poah", "john.poah@mail.com", 9517152);
        ResponseEntity<GeneralUserDto> response = requestClient.postForEntity("/generalUsers/create", generalUserDto,
                GeneralUserDto.class);
        assertNotNull(response);
        GeneralUserDto generalUser = response.getBody();
        assertNotNull(generalUser);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("John", generalUser.getFirstName());
        return generalUser.getEmail();
    }

    private int createDemoBooking(String email, int roomId, int paymentId) {
        BookingRequestDto bookingRequestDto = new BookingRequestDto(email, roomId, paymentId);
        ResponseEntity<BookingResponseDto> response = requestClient.postForEntity("/booking/create", bookingRequestDto,
                BookingResponseDto.class);
        assertNotNull(response);
        BookingResponseDto booking = response.getBody();
        assertNotNull(booking);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return booking.getBookingId();
    }

    private int createDemoRequest(String description, int bookingId, boolean isFufilled) {
        RequestRequestDto requestRequestDto = new RequestRequestDto(description, bookingId, isFufilled);
        ResponseEntity<RequestResponseDto> response = requestClient.postForEntity("/request/create", requestRequestDto,
                RequestResponseDto.class);
        assertNotNull(response);
        RequestResponseDto requestRes = response.getBody();
        assertNotNull(requestRes);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return requestRes.getRequestId();
    }

    @Test
    public void testCreateAndGetRequest() {
        String email = createDemoGeneralUser();
        int roomId = createDemoRoom();
        int paymentId = createDemoPayment();
        int bookingId = createDemoBooking(email, roomId, paymentId);
        String description = "I want a pineapple on my pizza please!";
        boolean isFufilled = false;
        
        int requestId = createDemoRequest(description, bookingId, isFufilled);

        RequestResponseDto requestRes = requestClient.getForObject("/requests/" + requestId, RequestResponseDto.class);
        assertNotNull(requestRes);
        assertEquals(description, requestRes.getDescription());
        assertEquals(isFufilled, requestRes.getIsFufilled());
        assertEquals(bookingId, requestRes.getBooking().getBookingId());
    }

    
}
