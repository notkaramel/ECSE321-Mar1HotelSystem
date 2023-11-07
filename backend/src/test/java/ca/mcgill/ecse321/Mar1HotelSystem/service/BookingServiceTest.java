package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.BookingRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.sql.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

/*
 * @Author: Bilar Mokhtari
 */
@SpringBootTest
public class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    /*
     * Test to make sure if the booking is created correctly.
     */
    @Test
    public void testCreateBooking() {
        String generalUserEmail = "john.doe@example.com";
        GeneralUser generalUser = new GeneralUser();
        generalUser.setEmail(generalUserEmail);
        int roomId = 1;
        Payment payment = new Payment();
        payment.setAmount(100);
        Room room = new Room();
        room.setRoomId(roomId);
        Booking booking = new Booking();
        booking.setGeneralUser(generalUser);
        booking.setRoom(room);
        booking.setPayment(payment);
        when(bookingRepository.save(booking)).thenReturn(booking);
        Booking createdBooking = bookingService.createBooking(booking.getPayment(), booking.getGeneralUser(), booking.getRoom());

        assertNotNull(createdBooking);
        assertEquals(generalUserEmail, createdBooking.getGeneralUser().getEmail());
        assertEquals(roomId, createdBooking.getRoom().getRoomId());
        assertEquals(booking.getBookingId(), createdBooking.getBookingId());
        assertEquals(100, createdBooking.getPayment().getAmount());
        


    
    }
    /*
     * Test to see if the booking is updated correctly.
     */
    @Test
    public void testUpdateBooking() {
        Booking booking = new Booking();
        booking.setBookingId(1);
        when(bookingRepository.save(booking)).thenReturn(booking);
        Booking updatedBooking = bookingService.updateBooking(booking);
        assertNotNull(updatedBooking);
        assertEquals(1, updatedBooking.getBookingId());
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
        String error = null;
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
            bookingService.createBooking(null, booking.getGeneralUser(), booking.getRoom());
        } catch (Exception e) {
            assertThrows(Mar1HotelSystemException.class, () -> bookingService.createBooking(null, generalUser, room));
        }
    }


    @Test
    public void testInvalidDeleteBooking() {
        Booking booking = new Booking();
        booking.setBookingId(1);
        when(bookingRepository.findBookingByBookingId(booking.getBookingId())).thenReturn(null);
        try {
             bookingService.deleteBooking(1);
        } catch (Exception e) {
            assertThrows(Mar1HotelSystemException.class, () -> bookingService.deleteBooking(1));
        }
    }

    @Test
    public void testInvalidUpdateBooking() {
        Booking booking = new Booking();
        booking.setBookingId(1);
        when(bookingRepository.findBookingByBookingId(booking.getBookingId())).thenReturn(null);
        try {
            bookingService.updateBooking(booking);
        } catch (Exception e) {
            assertThrows(Mar1HotelSystemException.class, () -> bookingService.updateBooking(booking));
        }
    }





}
