package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;

/**
 * The CRUD Repository Interface to store and retrieve all Hotel objects.
 * 
 * @author Antoine Phan (@notkaramel)
 */
public interface HotelRepository extends CrudRepository<Hotel, String> {
    /**
     * Find a Hotel object by its hotelName.
     * 
     * @param hotelName
     * @return the corresponding Hotel object
     */
    public Hotel findHotelByHotelName(String hotelName);

    /**
     * Find a Hotel object by its hotelSchedule.
     * 
     * @param hotelSchedule
     * @return the corresponding Hotel object
     */
    public Hotel findHotelByHotelSchedule(HotelSchedule hotelSchedule);
}