package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import java.util.ArrayList;
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

import ca.mcgill.ecse321.Mar1HotelSystem.dto.BookingRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.BookingResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.service.BookingService;
import ca.mcgill.ecse321.Mar1HotelSystem.service.HotelService;

/**
 * The controller that handles /booking endpoint requests
 * Functionalities:
 * - Get all bookings (GET /bookings)
 * - Get booking by ID (GET /bookings/{bookingId})
 * - Create a booking (POST /bookings)
 * - Request body: BookingRequestDto schema
 * - Update a booking (PUT /bookings/{bookingId})
 * - Request body: BookingRequestDto schema
 * - Delete a booking (DELETE /bookings/{bookingId})
 * 
 * @author: Bilar Mokhtari (@bmokhtari)
 */

@RestController
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    HotelService hotelService;

    @DeleteMapping(value = { "/booking/delete/{bookingId}", "/booking/{bookingId}/" })
    @ResponseStatus(HttpStatus.OK)
    public void deleteBooking(@PathVariable int bookingId) {
        bookingService.deleteBooking(bookingId);
    }

    @GetMapping(value = { "/booking/{bookingId}", "/booking/{bookingId}/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookingResponseDto> getBookingById(@PathVariable int bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        return new ResponseEntity<BookingResponseDto>(new BookingResponseDto(booking), HttpStatus.OK);
    }

    @GetMapping(value = { "/booking/all", "/booking/all" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookingResponseDto>> getAllBookings() {
        List<Booking> bookingList = bookingService.getAllBookings();
        List<BookingResponseDto> bookingResponseDtoList = new ArrayList<BookingResponseDto>();

        for (Booking booking : bookingList) {
            bookingResponseDtoList.add(new BookingResponseDto(booking));
        }
        return new ResponseEntity<List<BookingResponseDto>>(bookingResponseDtoList, HttpStatus.OK);
    }

    @PostMapping(value = { "/booking/create", "/booking/create" })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        Booking booking = bookingService.createBooking(bookingRequestDto.getGeneralUserEmail(),
                bookingRequestDto.getRoomId(), bookingRequestDto.getPaymentId());
        return new ResponseEntity<BookingResponseDto>(new BookingResponseDto(booking), HttpStatus.CREATED);
    }

    @PutMapping(value = { "/booking/update/{bookingId}", "/booking/update/{bookingId}/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookingResponseDto> updateBooking(@PathVariable int bookingId,
            @RequestBody BookingRequestDto bookingRequestDto) {
        Booking booking = bookingService.updateBooking(bookingId, bookingRequestDto.getGeneralUserEmail(),
                bookingRequestDto.getRoomId(), bookingRequestDto.getPaymentId());
        return new ResponseEntity<BookingResponseDto>(new BookingResponseDto(booking), HttpStatus.OK);
    }
}
