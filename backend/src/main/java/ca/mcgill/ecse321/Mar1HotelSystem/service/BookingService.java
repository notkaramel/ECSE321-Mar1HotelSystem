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
        Booking booking = new Booking();
        String errorCreate = "On createBooking: ";

        try {
            Payment payment = paymentRepository.findPaymentByPaymentId(paymentId);
            booking.setPayment(payment);
        } catch (Exception e) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, errorCreate + "Payment with id " + paymentId + " does not exist.");
        }
        try {
            GeneralUser generalUser = generalUserRepository.findGeneralUserByEmail(generalUserEmail);
            booking.setGeneralUser(generalUser);
        } catch (Exception e) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, errorCreate + "General user with email " + generalUserEmail + " does not exist.");
        }
        try {
            Room room = roomRepository.findRoomByRoomId(roomId);
            booking.setRoom(room);
        } catch (Exception e) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, errorCreate + "Room with id " + roomId + " does not exist.");
        }
        bookingRepository.save(booking);
        return booking;
    }

    /**
     * Service method to update an existing booking.
     */
    @Transactional
    public Booking updateBooking(int bookingId, String generalUserEmail, int roomId, int paymentId) {
        Booking bookingToUpdate = bookingRepository.findBookingByBookingId(bookingId);
        String errorUpdate = "On updateBooking: ";

        if(bookingToUpdate == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, errorUpdate + "Booking with id " + bookingId + " does not exist.");
        }

        Payment payment = paymentRepository.findPaymentByPaymentId(paymentId);
        if (payment == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, errorUpdate + "Payment with id " + paymentId + " does not exist.");
        }
        GeneralUser generalUser = generalUserRepository.findGeneralUserByEmail(generalUserEmail);
        if (generalUser == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, errorUpdate + "General user with email " + generalUserEmail + " does not exist.");
        }
        
        Room room = roomRepository.findRoomByRoomId(roomId);
        if (room == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, errorUpdate + "Room with id " + roomId + " does not exist.");
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
                    "Can't delete: Booking with id " + bookingId + " does not exist.");
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
                    "Can't get: Booking with id " + bookingId + " does not exist.");
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
