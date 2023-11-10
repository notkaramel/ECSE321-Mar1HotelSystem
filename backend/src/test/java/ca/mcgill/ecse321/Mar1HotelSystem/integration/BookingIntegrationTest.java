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
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;
import ca.mcgill.ecse321.Mar1HotelSystem.service.HotelService;
import ca.mcgill.ecse321.Mar1HotelSystem.service.RoomService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class BookingIntegrationTest {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TestRestTemplate bookingClient;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    HotelService hotelService;

    @Autowired
    GeneralUserRepository generalUserRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomService roomService;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        bookingRepository.deleteAll();
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

    public void testDeleteBookingByIdIntegration(int id) {
        ResponseEntity<Booking> response = bookingClient.exchange("/booking/" + id, HttpMethod.DELETE, null,
                Booking.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    public void testGetBookingByIdIntegration(int id) {
        ResponseEntity<Booking> response = bookingClient.getForEntity("/booking/" + id, Booking.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getBookingId());
    }

    // @Test
    // public void testCreateandDeleteBookingByIdIntegration() {
        
    //     BookingRequestDto bookingRequestDto = createBookingRequestDto(
    //         createPaymentRequestDto(100),
    //         createGeneralUserDto("Joe", "John", "joejohn@mail.com", 514514514),
    //         createRoomRequestDto(RoomType.Suite, BedType.King, true, 200, 2));
    //     int id = testCreateBookingIntegration(bookingRequestDto);
    //     testGetBookingByIdIntegration(id);
    //     testDeleteBookingByIdIntegration(id);
    // }

    public Room createRoom(RoomRequestDto roomRequestDto) {
        try {
            hotelService.getHotel();
        } catch (Mar1HotelSystemException e) {
            hotelService.createHotel();
        }
        Room.RoomType roomType = roomRequestDto.getRoomType();
        Room.BedType bedType = roomRequestDto.getBedType();
        boolean isAvailable = roomRequestDto.getIsAvailable();
        int pricePerNight = roomRequestDto.getPricePerNight();
        int maxCapacity = roomRequestDto.getMaxCapacity();

        Room room = roomService.createRoom(roomType, bedType, isAvailable, pricePerNight, maxCapacity);
        return room;

    }

    // public void setUp() {
    // RoomType roomType = RoomType.Suite;
    // BedType bedType = BedType.King;
    // boolean isAvailable = true;
    // int pricePerNight = 200;
    // int maxCapacity = 2;
    // createPaymentRequestDto(100);
    // createGeneralUserDto("Joe", "John", "joe@mail.com", 514514514);
    // createRoomRequestDto(roomType, bedType, isAvailable, pricePerNight,
    // maxCapacity);
    // }

    public GeneralUserDto createGeneralUserDto(String firstName, String lastName, String email, long phoneNumber) {
        GeneralUserDto generalUserDto = new GeneralUserDto();
        generalUserDto.setFirstName(firstName);
        generalUserDto.setLastName(lastName);
        generalUserDto.setEmail(email);
        generalUserDto.setPhoneNumber(phoneNumber);
        return generalUserDto;
    }

    public PaymentRequestDto createPaymentRequestDto(int amount) {
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setAmount(amount);
        return paymentRequestDto;
    }

    public RoomRequestDto createRoomRequestDto(Room.RoomType roomType, Room.BedType bedType, boolean isAvailable,
            int pricePerNight, int maxCapacity) {

        RoomRequestDto roomRequestDto = new RoomRequestDto(roomType, bedType, isAvailable, pricePerNight, maxCapacity);

        return roomRequestDto;

    }

    // public BookingRequestDto createBookingRequestDto(PaymentRequestDto paymentRequestDto, GeneralUserDto generalUserDto,
    //         RoomRequestDto roomRequestDto) {
    //     BookingRequestDto bookingRequestDto = new BookingRequestDto();
    //     bookingRequestDto.setPayment(paymentRequestDto);
    //     bookingRequestDto.setGeneralUser(generalUserDto);
    //     bookingRequestDto.setRoom(roomRequestDto);
    //     return bookingRequestDto;
    // }

}
