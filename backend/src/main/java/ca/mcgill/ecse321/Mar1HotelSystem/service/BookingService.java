package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.BookingRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.BookingRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
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

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    GeneralUserRepository   generalUserRepository;

    @Autowired
    PaymentRepository   paymentRepository;

    /**
     * Service method to create and save a new booking.
     */
    @Transactional
    public Booking createBooking(BookingRequestDto bookingRequestDto) {
        Booking booking = new Booking();
        booking.setPayment(paymentRepository.findPaymentByPaymentId(bookingRequestDto.getPaymentId()));
        booking.setGeneralUser(generalUserRepository.findGeneralUserByEmail(bookingRequestDto.getGeneralUserEmail()));
        booking.setRoom(roomRepository.findRoomByRoomId(bookingRequestDto.getRoomId()));
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
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Booking with id " + bookingId + " does not exist.");
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
