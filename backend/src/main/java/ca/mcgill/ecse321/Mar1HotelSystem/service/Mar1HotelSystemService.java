package ca.mcgill.ecse321.Mar1HotelSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelRepository;
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
    public Hotel getHotel() {
        return hotelRepository.findHotelByHotelName("Mar-1 Hotel");
    }

    @Transactional
    public Hotel getHotel(String hotelName) {
        // The hotel name should be "Mar-1 Hotel" by default
        return hotelRepository.findHotelByHotelName(hotelName);
    }
}
