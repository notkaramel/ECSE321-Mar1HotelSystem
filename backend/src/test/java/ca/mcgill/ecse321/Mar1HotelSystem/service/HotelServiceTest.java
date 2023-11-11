package ca.mcgill.ecse321.Mar1HotelSystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;

/**
 * Hotel Service Test
 * 
 * @author Antoine Phan (@notkaramel)
 */
@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    @Test
    public void testCreateHotel() {
        when(hotelRepository.save(isA(Hotel.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });

        Hotel hotel = hotelService.createHotel();
        assertNotNull(hotel);
        assertEquals("Mar-1 Hotel", hotel.getHotelName());
    }

    @Test
    public void testCreateHotelButHotelAlreadyExists() {
        HttpStatus errorStatus = null;
        when(hotelRepository.findHotelByHotelName(isA(String.class))).thenAnswer((InvocationOnMock invocation) -> {
            return new Hotel();
        });

        try {
            hotelService.createHotel();
        } catch (Mar1HotelSystemException e) {
            assertEquals("Can't create hotel", e.getMessage());
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            errorStatus = e.getStatus();
        }
        // Making sure that Exception was thrown correctly
        assertEquals(HttpStatus.BAD_REQUEST, errorStatus);
    }

    @Test
    public void testGetHotel() {
        when(hotelRepository.findHotelByHotelName(isA(String.class))).thenAnswer((InvocationOnMock invocation) -> {
            return new Hotel();
        });

        Hotel hotel = hotelService.getHotel();
        assertNotNull(hotel);
        assertEquals("Mar-1 Hotel", hotel.getHotelName());
    }

    @Test
    public void testGetHotelButItsNull() {
        HttpStatus errorStatus = null;
        when(hotelRepository.findHotelByHotelName(isA(String.class))).thenAnswer((InvocationOnMock invocation) -> {
            return null;
        });

        try {
            hotelService.getHotel();
        } catch (Mar1HotelSystemException e) {
            assertEquals("Can't find hotel", e.getMessage());
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            errorStatus = e.getStatus();
        }
        // Making sure that Exception was thrown correctly
        assertEquals(HttpStatus.BAD_REQUEST, errorStatus);
    }

}
