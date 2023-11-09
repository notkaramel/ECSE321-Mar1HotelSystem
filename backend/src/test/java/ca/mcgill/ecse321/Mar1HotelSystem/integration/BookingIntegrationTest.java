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
import ca.mcgill.ecse321.Mar1HotelSystem.dto.GeneralUserDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.PaymentRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class BookingIntegrationTest {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TestRestTemplate bookingClient;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    GeneralUserRepository generalUserRepository;

    @Autowired
    RoomRepository roomRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        bookingRepository.deleteAll();
    }

    public BookingRequestDto createBookingRequestDto() {

        int paymentId = 1;

        String generalUserEmail = "john.doe@example.com"; // Set the email as needed

        int roomId = 1;

        BookingRequestDto bookingRequestDto = new BookingRequestDto(paymentId, generalUserEmail, roomId);

        return bookingRequestDto;
    }

    public int testCreateBookingIntegration(BookingRequestDto bookingRequestDto) {
        ResponseEntity<Booking> response = bookingClient.postForEntity("/booking/create", bookingRequestDto,
                Booking.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        int bookingId = response.getBody().getBookingId();
        return bookingId;
    }

    public void testGetBookingByIdIntegration(int id) {
        ResponseEntity<Booking> response = bookingClient.getForEntity("/booking/" + id, Booking.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getBookingId());
    }

    @Test
    public void testCreateandDeleteBookingByIdIntegration() {
        setUp();
        BookingRequestDto bookingRequestDto = createBookingRequestDto();
        int id = testCreateBookingIntegration(bookingRequestDto);
        ResponseEntity<Void> res = bookingClient.exchange(
                "/booking/" + id,
                HttpMethod.DELETE,
                null,
                Void.class);
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    public void setUp(){
        GeneralUser generalUser = new GeneralUser();
        generalUser.setFistName("John");
        generalUser.setLastName("Doe");
        generalUser.setEmail("johndoe@email.com");
        generalUser.setPhoneNumber(1234567890);
        generalUserRepository.save(generalUser);

        Payment payment = new Payment();
        payment.setPaymentId(1);
        payment.setAmount(100);
        paymentRepository.save(payment);

        Room room = new Room();
        room.setRoomId(1);
        room.setRoomType(RoomType.Deluxe);
        room.setBedType(BedType.Doubles);
        room.setPricePerNight(100);
        room.setIsAvailable(true);
        room.setMaxCapacity(1);
        roomRepository.save(room);

    }

}
