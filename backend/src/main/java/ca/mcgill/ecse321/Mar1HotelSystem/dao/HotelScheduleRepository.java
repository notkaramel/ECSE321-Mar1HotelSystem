package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;

/**
 * The CRUD Repository Interface to store and retrieve all HotelSchedule
 * objects.
 * 
 * @autor Antoine Phan (@notkaramel)
 */
public interface HotelScheduleRepository extends CrudRepository<HotelSchedule, Integer> {
    /**
     * Find a HotelSchedule object by its year attribute.
     * 
     * @param year
     * @return the corresponding HotelSchedule object
     */
    public HotelSchedule findHotelScheduleByYear(int year);
}
