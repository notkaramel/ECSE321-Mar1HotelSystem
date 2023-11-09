package ca.mcgill.ecse321.Mar1HotelSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import jakarta.transaction.Transactional;

/**
 * Serivce class for the main endpoint of the application
 * Functionalities:
 * - Handle Hotel (e.g., Send Hotel Object)
 * 
 * @author Antoine Phan (@notkaramel) 
 */
@Service
public class Mar1HotelSystemService {
     @Autowired
    HotelRepository hotelRepository;

    @Transactional
    public Hotel createHotel() {
        Hotel hotel = hotelRepository.findHotelByHotelName("Mar-1 Hotel");
        if (hotel != null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Can't create hotel");
        }
        
        hotel = new Hotel();
        hotelRepository.save(hotel);
        return hotel;
    }

    @Transactional
    public Hotel getHotel() {
        Hotel hotel = hotelRepository.findHotelByHotelName("Mar-1 Hotel");
        if (hotel == null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Can't find hotel");
        }
        return hotel;
    }
}
