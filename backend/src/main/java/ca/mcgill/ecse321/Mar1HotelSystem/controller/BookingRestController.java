package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.service.BookingService;

@RestController
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

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
}
