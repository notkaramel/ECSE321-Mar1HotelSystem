package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.BookingRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * This class contains tests for the BookingService class.
 * 
 * @author: Bilar Mokhtari (@bmokhtari)
 * @author: Antoine Phan (@notkaramel)
 */
@SpringBootTest
public class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private GeneralUserRepository generalUserRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    final String generalUserEmail = "john.doe@example.com";
    final int roomId = 1;
    final int paymentId = 132;

    @BeforeEach
    private void setMockOutput() {
        // Booking will take user email from this user
        GeneralUser generalUser = new GeneralUser("John", "Doe", generalUserEmail, 1248274);

        Payment payment = new Payment(100);
        payment.setPaymentId(paymentId);

        // Room is dependent on hotel
        when(hotelRepository.findHotelByHotelName("Mar-1 Hotel")).thenReturn(new Hotel());
        Room room = new Room(RoomType.Deluxe, Room.BedType.King, true, 100, 4, hotelService.getHotel());
        room.setRoomId(roomId);

        // Set mock behaviour of dependent classes
        when(roomRepository.findRoomByRoomId(roomId)).thenReturn(room);
        when(generalUserRepository.findGeneralUserByEmail("john.doe@example.com")).thenReturn(generalUser);
        when(paymentRepository.findPaymentByPaymentId(paymentId)).thenReturn(payment);

        // Set mock behaviour of bookingRepository
        when(bookingRepository.save(isA(Booking.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });
    }

    /*
     * Test to make sure if the booking is created correctly.
     */
    @Test
    public void testCreateBooking() {
        Booking createdBooking = bookingService.createBooking(generalUserEmail, roomId, paymentId);

        assertNotNull(createdBooking);
        // Check for correct values of the booking
        assertEquals(generalUserEmail, createdBooking.getGeneralUser().getEmail());
        Room room = roomRepository.findRoomByRoomId(roomId);
        assertEquals(room.getRoomType(), createdBooking.getRoom().getRoomType());
        assertEquals(room.getPricePerNight(), createdBooking.getRoom().getPricePerNight());
        assertEquals(100, createdBooking.getPayment().getAmount());
    }

    /*
     * Test to see if the booking is updated correctly.
     */
    @Test
    public void testUpdateBookingPaymentInfo() {
        GeneralUser generalUser = new GeneralUser(generalUserEmail, "John", "Doe", 1248274);
        Payment payment = new Payment(100);
        payment.setPaymentId(paymentId);
        Room room = new Room(RoomType.Deluxe, Room.BedType.King, true, 100, 4, hotelService.getHotel());
        room.setRoomId(roomId);

        Booking booking = new Booking(payment, generalUser, room);
        booking.setBookingId(1);

        // mock database behaviour
        when(bookingRepository.findBookingByBookingId(1)).thenReturn(booking);

        Payment newPayment = new Payment(200);
        int newPaymentId = 571;
        newPayment.setPaymentId(newPaymentId);
        when(paymentRepository.findPaymentByPaymentId(571)).thenReturn(newPayment);

        Booking updatedBooking = bookingService.updateBooking(1, generalUserEmail, roomId, newPaymentId);
        assertNotNull(updatedBooking);
        assertEquals(1, updatedBooking.getBookingId());
        assertEquals(newPaymentId, updatedBooking.getPayment().getPaymentId());
        assertEquals(200, updatedBooking.getPayment().getAmount());
    }

    /*
     * Test to see if the booking is deleted correctly.
     */
    @Test
    public void testDeleteBooking() {
        Booking booking = new Booking();
        booking.setBookingId(1);
        when(bookingRepository.findBookingByBookingId(booking.getBookingId())).thenReturn(booking);
        doNothing().when(bookingRepository).delete(booking);
        bookingService.deleteBooking(1);
        List<Booking> bookings = ServiceUtils.toList(bookingService.getAllBookings());
        assertEquals(0, bookings.size());
    }

    /*
     * Test to see if you can get a booking by its ID.
     */
    @Test
    public void testGetBookingById() {
        Booking booking = new Booking();
        booking.setBookingId(1);
        when(bookingRepository.findBookingByBookingId(1)).thenReturn(booking);
        Booking foundBooking = bookingService.getBookingById(1);
        assertNotNull(foundBooking);
        assertEquals(1, foundBooking.getBookingId());
    }

    /*
     * Test to see if bookings are stored correctly and you can get them all.
     */
    @Test
    public void testGetAllBookings() {
        Booking booking1 = new Booking();
        Booking booking2 = new Booking();
        when(bookingRepository.findAll()).thenReturn(List.of(booking1, booking2));
        List<Booking> bookings = ServiceUtils.toList(bookingService.getAllBookings());
        assertEquals(2, bookings.size());
    }

    @Test
    public void testCreateInvalidBooking() {
        String generalUserEmail = "";
        GeneralUser generalUser = new GeneralUser();
        generalUser.setEmail(generalUserEmail);
        int roomId = 1;
        Room room = new Room();
        room.setRoomId(roomId);
        Booking booking = new Booking();
        booking.setGeneralUser(generalUser);
        booking.setRoom(room);
        try {
            bookingService.createBooking(generalUserEmail, roomId, paymentId);
        } catch (Exception e) {
            assertEquals(Mar1HotelSystemException.class, e.getClass());
            assertEquals(e.getMessage(),
                    "On createBooking: General user with email " + generalUserEmail + " does not exist.");
        }
    }

    @Test
    public void testInvalidDeleteBooking() {
        Booking booking = new Booking();
        booking.setBookingId(1);
        when(bookingRepository.findBookingByBookingId(booking.getBookingId())).thenReturn(null);
        assertThrows(Mar1HotelSystemException.class, () -> bookingService.deleteBooking(1));
    }

    @Test
    public void testUpdateBookingInvalidId() {
        Booking booking = new Booking();
        int invalidBookingId = 5128;
        booking.setBookingId(invalidBookingId);
        // Mock Behavior: Can't find booking with id 5128
        when(bookingRepository.findBookingByBookingId(booking.getBookingId())).thenReturn(null);
        try {
            bookingService.updateBooking(invalidBookingId, generalUserEmail, roomId, paymentId);
        } catch (Mar1HotelSystemException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
            assertEquals("On updateBooking: Booking with id " + invalidBookingId + " does not exist.", e.getMessage());
        }

    }

    @Test
    public void testUpdateBookingWithInvalidPayment() {
        int invalidPaymentId = 243;

        when(paymentRepository.findPaymentByPaymentId(invalidPaymentId)).thenReturn(null);
        when(bookingRepository.findBookingByBookingId(1)).thenReturn(new Booking());

        assertThrows(Mar1HotelSystemException.class,
                () -> bookingService.updateBooking(1, generalUserEmail, roomId, invalidPaymentId));
    }

    @Test
    public void testUpdateBookingWithInvalidRoom() {
        int invalidRoomId = 3;
        when(roomRepository.findRoomByRoomId(invalidRoomId)).thenReturn(null);
        when(bookingRepository.findBookingByBookingId(1)).thenReturn(new Booking());

        assertThrows(Mar1HotelSystemException.class,
                () -> bookingService.updateBooking(1, generalUserEmail, invalidRoomId, paymentId));
    }

    @Test
    public void testUpdateBookingWithInvalidGeneralUser() {
        String invalidGeneralUserEmail = "helloworld@gmail.com";
        when(generalUserRepository.findGeneralUserByEmail(invalidGeneralUserEmail)).thenReturn(null);
        when(bookingRepository.findBookingByBookingId(1)).thenReturn(new Booking());

        assertThrows(Mar1HotelSystemException.class,
                () -> bookingService.updateBooking(1, invalidGeneralUserEmail, roomId, paymentId));
    }
}
