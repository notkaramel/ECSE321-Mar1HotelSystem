package ca.mcgill.ecse321.Mar1HotelSystem.service;

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
     * Service method to create and save a new booking..
     */
    @Transactional
    public Booking createBooking(Payment payment, GeneralUser generalUser, Room room) {
        if (bookingRepository.findBookingByBookingId(booking.getBookingId()) != null) {
            return null;  // Booking with this ID already exists.
        }
        return bookingRepository.save(booking);
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
     * @return 
     */
    @Transactional
    public boolean deleteBooking(int bookingId) {
        Booking booking = getBookingById(bookingId);
        try {
            bookingRepository.delete(booking);
        } catch (Exception e) {
            return false;
        }
        return true;
    };
    

    /**
     * Service method to retrieve a booking by ID.
     */
    @Transactional
    public Booking getBookingById(int bookingId) {
        return bookingRepository.findBookingByBookingId(bookingId);
    }

    /**
     * Service method to retrieve all bookings.
     */
    @Transactional
    public Iterable<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
