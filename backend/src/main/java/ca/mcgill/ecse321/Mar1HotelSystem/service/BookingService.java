package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.BookingRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

/**
 * Service class to manage Booking Entities
 * 
 * @author Bilar Mokhtari
 */
@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    /**
     * Service method to create and save a new booking.
     */
    @Transactional
    public Booking createBooking(Payment payment, GeneralUser generalUser, Room room) {
        Booking booking = new Booking();
        booking.setPayment(payment);
        booking.setGeneralUser(generalUser);
        booking.setRoom(room);
        bookingRepository.save(booking);
        return booking;
    }

    /**
     * Service method to update an existing booking.
     */
    @Transactional
    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    /**
     * Service method to delete a booking. 
     */
    @Transactional
    public void deleteBooking(int bookingId) {
        Booking booking = this.getBookingById(bookingId);
        bookingRepository.delete(booking);

    };
    

    /**
     * Service method to retrieve a booking by ID.
     */
    @Transactional
    public Booking getBookingById(int bookingId) {
        Booking booking = bookingRepository.findBookingByBookingId(bookingId);
        if(booking == null) {
            throw new IllegalArgumentException("Booking does not exist.");
        }
        return booking;
    }

    /**
     * Service method to retrieve all bookings.
     */
    @Transactional
    public Iterable<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
  }
