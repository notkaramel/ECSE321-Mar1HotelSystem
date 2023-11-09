package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.BookingRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.CustomerDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.service.BookingService;
import ca.mcgill.ecse321.Mar1HotelSystem.service.RoomService;

/**
 * The controller that handles /booking endpoint requests
 * Functionalities:
 * - Get all bookings (GET /bookings)
 * - Get booking by ID (GET /bookings/{bookingId})
 * - Create a booking (POST /bookings)
 *   - Request body: BookingRequestDto schema
 * - Update a booking (PUT /bookings/{bookingId})
 *   - Request body: BookingRequestDto schema
 * - Delete a booking (DELETE /bookings/{bookingId})
 * 
 * @bmokhtari Bilar Mokhtari
 */

@RestController
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    GeneralUserRepository generalUserRepository;

    @Autowired
    RoomRepository roomRepository;

    private RoomRequestDto roomRequestDto;

    @DeleteMapping(value = { "/booking/{bookingId}", "/booking/{bookingId}/" })
    public ResponseEntity<Void> deleteBooking(@PathVariable int bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = { "/booking/{bookingId}", "/booking/{bookingId}/" })
    public ResponseEntity<Booking> getBookingById(@PathVariable int bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    // @GetMapping(value = { "/booking", "/booking/" })
    // public ResponseEntity<List<Booking>> getAllBookings() {
    //     List<Booking> bookings = bookingService.getAllBookings();
    //     return new ResponseEntity<>(bookings, HttpStatus.OK);
    // }

    @PostMapping(value = { "/booking/create", "/booking/create" })
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        Booking booking = bookingService.createBooking(bookingRequestDto);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }
    
    @PutMapping(value = { "/booking/{bookingId}", "/booking/{bookingId}/" })
    public ResponseEntity<Booking> updateBooking(@PathVariable int bookingId, @RequestBody BookingRequestDto bookingRequestDto) {
        Booking booking = bookingService.getBookingById(bookingId);
        booking.setPayment(paymentRepository.findPaymentByPaymentId(bookingRequestDto.getPaymentId()));
        booking.setGeneralUser(generalUserRepository.findGeneralUserByEmail(bookingRequestDto.getGeneralUserEmail()));
        booking.setRoom(roomRepository.findRoomByRoomId(bookingRequestDto.getRoomId()));
        booking = bookingService.updateBooking(booking);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
}