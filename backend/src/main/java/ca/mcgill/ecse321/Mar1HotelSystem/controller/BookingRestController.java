package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.service.BookingService;

@RestController
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

    @DeleteMapping(value = { "/booking/{bookingId}", "/booking/{bookingId}/" })
    public void deleteBooking(@PathVariable int bookingId) {
        bookingService.deleteBooking(bookingId);
    }
}
