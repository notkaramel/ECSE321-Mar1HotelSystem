package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, String> {
    Hotel getHotel();
}