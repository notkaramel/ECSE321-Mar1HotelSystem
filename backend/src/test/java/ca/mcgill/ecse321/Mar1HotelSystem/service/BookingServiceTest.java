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
        try {
            Booking createdBooking = bookingService.createBooking(generalUserEmail, roomId, paymentId);
            assertNotNull(createdBooking);
            // Check for correct values of the booking
            assertEquals(generalUserEmail, createdBooking.getGeneralUser().getEmail());
            Room room = roomRepository.findRoomByRoomId(roomId);
            assertEquals(room.getRoomType(), createdBooking.getRoom().getRoomType());
            assertEquals(room.getPricePerNight(), createdBooking.getRoom().getPricePerNight());
            assertEquals(100, createdBooking.getPayment().getAmount());
        } catch (Exception e) {
            fail();
        }
    }

    /*
     * Test to see if the booking is updated correctly.
     */
    @Test
    public void testUpdateBookingPaymentInfo() {
        try {
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
        } catch (Exception e) {
            fail();
        }
    }

    /*
     * Test to see if the booking is deleted correctly.
     */
    @Test
    public void testDeleteBooking() {
        try {
            Booking booking = new Booking();
            booking.setBookingId(1);
            when(bookingRepository.findBookingByBookingId(booking.getBookingId())).thenReturn(booking);
            doNothing().when(bookingRepository).delete(booking);
            bookingService.deleteBooking(1);
            List<Booking> bookings = ServiceUtils.toList(bookingService.getAllBookings());
            assertEquals(0, bookings.size());
        } catch (Exception e) {
            fail();
        }
    }

    /*
     * Test to see if you can get a booking by its ID.
     */
    @Test
    public void testGetBookingById() {
        try {
            Booking booking = new Booking();
            booking.setBookingId(1);
            when(bookingRepository.findBookingByBookingId(1)).thenReturn(booking);
            Booking foundBooking = bookingService.getBookingById(1);
            assertNotNull(foundBooking);
            assertEquals(1, foundBooking.getBookingId());
        } catch (Exception e) {
            fail();
        }
    }

    /*
     * Test to see if bookings are stored correctly and you can get them all.
     */
    @Test
    public void testGetAllBookings() {
        try {
            Booking booking1 = new Booking();
            Booking booking2 = new Booking();
            when(bookingRepository.findAll()).thenReturn(List.of(booking1, booking2));
            List<Booking> bookings = ServiceUtils.toList(bookingService.getAllBookings());
            assertEquals(2, bookings.size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testCreateInvalidBooking() {
        String invalidEmail = "blehbleh";
        Mar1HotelSystemException error = null;
        try {
            bookingService.createBooking(invalidEmail, roomId, paymentId);
        } catch (Mar1HotelSystemException e) {
            error = e;
        }
        assertEquals(HttpStatus.NOT_FOUND, error.getStatus());
        assertEquals("On create booking: General user with email " + invalidEmail + " cannot be found.",
                error.getMessage());
    }

    @Test
    public void testInvalidDeleteBooking() {
        int invalidBookingId = 5128;
        Mar1HotelSystemException error = null;

        when(bookingRepository.findBookingByBookingId(invalidBookingId)).thenReturn(null);
        try {
            bookingService.deleteBooking(invalidBookingId);
        } catch (Mar1HotelSystemException e) {
            error = e;
        }
        assertEquals(HttpStatus.NOT_FOUND, error.getStatus());
        assertEquals("Cannot delete booking with id " + invalidBookingId + ": booking was not found.",
                error.getMessage());
    }

    @Test
    public void testUpdateBookingInvalidId() {
        int invalidBookingId = 5128;
        Mar1HotelSystemException error = null;

        // Mock Behavior: Can't find booking with id 5128
        when(bookingRepository.findBookingByBookingId(invalidBookingId)).thenReturn(null);
        try {
            bookingService.updateBooking(invalidBookingId, generalUserEmail, roomId, paymentId);
        } catch (Mar1HotelSystemException e) {
            error = e;
        }
        assertEquals(HttpStatus.NOT_FOUND, error.getStatus());
        assertEquals("On update booking: booking with id " + invalidBookingId + " cannot be found.",
                error.getMessage());
    }

    @Test
    public void testUpdateBookingWithInvalidPayment() {
        int invalidPaymentId = 243;
        Mar1HotelSystemException error = null;

        when(paymentRepository.findPaymentByPaymentId(invalidPaymentId)).thenReturn(null);
        when(bookingRepository.findBookingByBookingId(1)).thenReturn(new Booking());
        try {
            bookingService.updateBooking(1, generalUserEmail, roomId, invalidPaymentId);
        } catch (Mar1HotelSystemException e) {
            error = e;
        }
        assertEquals(HttpStatus.NOT_FOUND, error.getStatus());
        assertEquals("On update booking: payment with id " + invalidPaymentId + " cannot be found.",
                error.getMessage());
    }

    @Test
    public void testUpdateBookingWithInvalidRoom() {
        Mar1HotelSystemException error = null;

        int invalidRoomId = 3;
        when(roomRepository.findRoomByRoomId(invalidRoomId)).thenReturn(null);
        when(bookingRepository.findBookingByBookingId(1)).thenReturn(new Booking());

        try {
            bookingService.updateBooking(1, generalUserEmail, invalidRoomId, paymentId);
        } catch (Mar1HotelSystemException e) {
            error = e;
        }
        assertEquals(HttpStatus.NOT_FOUND, error.getStatus());
        assertEquals("On update booking: room with id " + invalidRoomId + " cannot be found.", error.getMessage());
    }

    @Test
    public void testUpdateBookingWithInvalidGeneralUser() {
        String invalidGeneralUserEmail = "helloworld@gmail.com";
        Mar1HotelSystemException error = null;

        when(generalUserRepository.findGeneralUserByEmail(invalidGeneralUserEmail)).thenReturn(null);
        when(bookingRepository.findBookingByBookingId(1)).thenReturn(new Booking());

        try {
            bookingService.updateBooking(1, invalidGeneralUserEmail, roomId, paymentId);
        } catch (Mar1HotelSystemException e) {
            error = e;
        }
        assertEquals(HttpStatus.NOT_FOUND, error.getStatus());
        assertEquals("On update booking: general user with email " + invalidGeneralUserEmail + " cannot be found.",
                error.getMessage());
    }
}
