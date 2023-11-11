package ca.mcgill.ecse321.Mar1HotelSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.BookingRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

import java.util.List;
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
    GeneralUserRepository generalUserRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    PaymentRepository paymentRepository;

    /**
     * Service method to create and save a new booking.
     */
    @Transactional
    public Booking createBooking(String generalUserEmail, int roomId, int paymentId) {
        Payment payment = paymentRepository.findPaymentByPaymentId(paymentId);
        if(payment == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "On create booking: Payment with id " + paymentId + " cannot be found.");
        }
        
        GeneralUser generalUser = generalUserRepository.findGeneralUserByEmail(generalUserEmail);
        if (generalUser == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "On create booking: General user with email " + generalUserEmail + " cannot be found.");
        }
        
        Room room = roomRepository.findRoomByRoomId(roomId);
        if (room == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "On create booking: Room with id " + roomId + " cannot be found.");
        }
        Booking booking = new Booking(payment, generalUser, room);
        bookingRepository.save(booking);
        return booking;
    }

    /**
     * Service method to update an existing booking.
     */
    @Transactional
    public Booking updateBooking(int bookingId, String generalUserEmail, int roomId, int paymentId) {
        Booking bookingToUpdate = bookingRepository.findBookingByBookingId(bookingId);
        if(bookingToUpdate == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "On update booking: booking with id " + bookingId + " cannot be found.");
        }

        Payment payment = paymentRepository.findPaymentByPaymentId(paymentId);
        if (payment == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "On update booking: payment with id " + paymentId + " cannot be found.");
        }

        GeneralUser generalUser = generalUserRepository.findGeneralUserByEmail(generalUserEmail);
        if (generalUser == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "On update booking: general user with email " + generalUserEmail + " cannot be found.");
        }

        Room room = roomRepository.findRoomByRoomId(roomId);
        if (room == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "On update booking: room with id " + roomId + " cannot be found.");
        }
        
        bookingToUpdate.setPayment(payment);
        bookingToUpdate.setGeneralUser(generalUser);
        bookingToUpdate.setRoom(room);
        
        return bookingRepository.save(bookingToUpdate);
    }

    /**
     * Service method to delete a booking.
     */
    @Transactional
    public Booking deleteBooking(int bookingId) {
        Booking booking = bookingRepository.findBookingByBookingId(bookingId);
        if (booking == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND,
                    "Cannot delete booking with id " + bookingId + ": booking was not found.");
        }
        bookingRepository.delete(booking);
        return booking;
    };

    /**
     * Service method to retrieve a booking by ID.
     */
    @Transactional
    public Booking getBookingById(int bookingId) {
        Booking booking = bookingRepository.findBookingByBookingId(bookingId);
        if (booking == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND,
                    "Cannot get booking with id " + bookingId + ": booking was not found.");
        }
        return booking;
    }

    /**
     * Service method to retrieve all bookings.
     */
    @Transactional
    public List<Booking> getAllBookings() {
        return ServiceUtils.toList(bookingRepository.findAll());
    }
}
