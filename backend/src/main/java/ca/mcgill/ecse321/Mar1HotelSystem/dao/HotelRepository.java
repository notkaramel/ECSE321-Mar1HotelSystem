package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;

public interface HotelRepository extends CrudRepository<Hotel, String> {
    public Hotel findHotelByHotelName(String hotelName);    
    public Hotel findHotelByHotelSchedule(HotelSchedule hotelSchedule);
}