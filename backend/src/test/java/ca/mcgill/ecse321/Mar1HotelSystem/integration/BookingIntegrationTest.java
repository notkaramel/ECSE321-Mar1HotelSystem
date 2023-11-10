package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.BookingRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.BookingRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.BookingResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.GeneralUserDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.PaymentRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.PaymentResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingIntegrationTest {
    @Autowired
    private TestRestTemplate bookingClient;

    // Wiring repositories
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    GeneralUserRepository generalUserRepository;
    @Autowired
    PaymentRepository paymentRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        bookingRepository.deleteAll();
        paymentRepository.deleteAll();
        generalUserRepository.deleteAll();
        roomRepository.deleteAll();
    }

    private int createDemoRoom() {
        RoomRequestDto roomRequestDto = new RoomRequestDto(Room.RoomType.Deluxe, Room.BedType.King, true, 200, 4);
        ResponseEntity<RoomResponseDto> response = bookingClient.postForEntity("/room/create", roomRequestDto,
                RoomResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getRoomId();
    }

    private int createDemoPayment() {
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(100);
        ResponseEntity<PaymentResponseDto> response = bookingClient.postForEntity("/payment/create", paymentRequestDto,
                PaymentResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getPaymentId();
    }

    private String createDemoGeneralUser() {
        GeneralUserDto generalUserDto = new GeneralUserDto("John", "Poah", "john.poah@mail.com", 9517152);
        ResponseEntity<GeneralUserDto> response = bookingClient.postForEntity("/generalUsers/create", generalUserDto,
                GeneralUserDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getEmail();
    }

    private int createDemoBooking(String email, int roomId, int paymentId) {
        BookingRequestDto bookingRequestDto = new BookingRequestDto(email, roomId, paymentId);
        ResponseEntity<BookingResponseDto> response = bookingClient.postForEntity("/booking/create", bookingRequestDto,
                BookingResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        int bookingId = response.getBody().getBookingId();
        return bookingId;
    }

    private Booking getDemoBookingById(int id) {
        ResponseEntity<BookingResponseDto> response = bookingClient.getForEntity("/booking/" + id, BookingResponseDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getBookingId());
        Booking booking = new Booking();
        booking.setBookingId(response.getBody().getBookingId());
        booking.setRoom(response.getBody().getRoom());
        booking.setPayment(response.getBody().getPayment());
        booking.setGeneralUser(response.getBody().getGeneralUser());

        return booking;
    }

    @Test
    public void testCreateBooking() {
        int roomId = createDemoRoom();
        int paymentId = createDemoPayment();
        String email = createDemoGeneralUser();
        int bookingId = createDemoBooking(email, roomId, paymentId);
        assertNotNull(bookingId);
        assertEquals(1, bookingRepository.count());
    }

    public void deleteBookingById(int id) {
        ResponseEntity<Booking> response = bookingClient.exchange("/booking/delete/" + id, HttpMethod.DELETE, null,
                Booking.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreateAndRetrieveBookingById() {
        int roomId = createDemoRoom();
        int paymentId = createDemoPayment();
        String email = createDemoGeneralUser();
        int bookingId = createDemoBooking(email, roomId, paymentId);
        Booking booking = getDemoBookingById(bookingId);
        assertNotNull(booking);
        assertEquals(bookingId, booking.getBookingId());
        assertEquals(email, booking.getGeneralUser().getEmail());
        assertEquals(roomId, booking.getRoom().getRoomId());
    }

    @Test
    public void testCreateAndDeleteBookingById() {
        int roomId = createDemoRoom();
        int paymentId = createDemoPayment();
        String email = createDemoGeneralUser();
        int bookingId = createDemoBooking(email, roomId, paymentId);
        deleteBookingById(bookingId);
        assertEquals(0, bookingRepository.count());
    }

    private void updateBookingById(int id, String email, int roomId, int paymentId) {
        BookingRequestDto bookingRequestDto = new BookingRequestDto(email, roomId, paymentId);
        bookingClient.put("/booking/update/" + id, bookingRequestDto);
    }

    @Test 
    public void testCreateAndUpdateBookingById() {
        int roomId = createDemoRoom();
        int paymentId = createDemoPayment();
        String email = createDemoGeneralUser();
        int bookingId = createDemoBooking(email, roomId, paymentId);
        
        // Create new BookingRequestDto to update
        int newRoomId = createDemoRoom();
        int newPaymentId = createDemoPayment();
        updateBookingById(bookingId, email, newRoomId, newPaymentId);
        Booking updatedBooking = getDemoBookingById(bookingId);
        assertEquals(newRoomId, updatedBooking.getRoom().getRoomId());
        assertEquals(newPaymentId, updatedBooking.getPayment().getPaymentId());
    }

}
