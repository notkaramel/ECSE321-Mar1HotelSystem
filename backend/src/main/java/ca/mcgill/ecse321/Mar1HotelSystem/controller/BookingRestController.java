package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.dto.BookingRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.CustomerDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomDto;
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

    private RoomDto roomRequestDto;

    @DeleteMapping(value = { "/booking/{bookingId}", "/booking/{bookingId}/" })
    @ResponseStatus(HttpStatus.OK)
    public void deleteBooking(@PathVariable int bookingId) {
        bookingService.deleteBooking(bookingId);
    }

    @GetMapping(value = { "/booking/{bookingId}", "/booking/{bookingId}/" })
    @ResponseStatus(HttpStatus.OK)
    public void getBookingById(@PathVariable int bookingId) {
        bookingService.getBookingById(bookingId);
    }

    @GetMapping(value = { "/booking", "/booking/" })
    @ResponseStatus(HttpStatus.OK)
    public void getAllBookings() {
        bookingService.getAllBookings();
    }

    @PostMapping(value = { "/booking/create", "/booking/create" })
    @ResponseStatus(HttpStatus.CREATED)
    public void createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        GeneralUser generalUser = new GeneralUser(bookingRequestDto.getGeneralUser().getFirstName(), bookingRequestDto.getGeneralUser().getLastName(), bookingRequestDto.getGeneralUser().getEmail(), bookingRequestDto.getGeneralUser().getPhoneNumber());
        Payment payment = new Payment(bookingRequestDto.getPayment().getAmount());
        Room room = convertRoom(bookingRequestDto);
        bookingService.createBooking(payment, generalUser, room);
    }

    @PutMapping(value = { "/booking/{bookingId}", "/booking/{bookingId}/" })
    @ResponseStatus(HttpStatus.OK)
    public void updateBooking(@PathVariable int bookingId, @RequestBody BookingRequestDto bookingRequestDto) {
        GeneralUser generalUser = new GeneralUser(bookingRequestDto.getGeneralUser().getFirstName(), bookingRequestDto.getGeneralUser().getLastName(), bookingRequestDto.getGeneralUser().getEmail(), bookingRequestDto.getGeneralUser().getPhoneNumber());
        Payment payment = new Payment(bookingRequestDto.getPayment().getAmount());
        Booking booking = bookingService.getBookingById(bookingId);
        booking.setGeneralUser(generalUser);
        booking.setPayment(payment);
        booking.setRoom(convertRoom(bookingRequestDto)); 
        bookingService.updateBooking(booking);
    }


    private Room convertRoom (BookingRequestDto bookingRequestDto) {
        Room.RoomType roomType = roomRequestDto.getRoomType();
        Room.BedType bedType = roomRequestDto.getBedType();
        boolean isAvailable = roomRequestDto.getIsAvailable();
        int pricePerNight = roomRequestDto.getPricePerNight();
        int maxCapacity = roomRequestDto.getMaxCapacity();

        Room room = roomService.createRoom(roomType, bedType, isAvailable, pricePerNight, maxCapacity);
        return room;
    }
 }
