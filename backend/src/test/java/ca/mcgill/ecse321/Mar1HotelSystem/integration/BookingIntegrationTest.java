package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.BookingRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.BookingRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;

public class BookingIntegrationTest {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TestRestTemplate bookingClient;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        bookingRepository.deleteAll();
    }

    @Test
    public int testCreateBookingIntegration(BookingRequestDto bookingRequestDto) {
        Booking booking = new Booking();
        ResponseEntity<Booking> response = bookingClient.postForEntity("/booking/create", booking, Booking.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        int bookingId = response.getBody().getBookingId();
        return bookingId;
    }

    
}
