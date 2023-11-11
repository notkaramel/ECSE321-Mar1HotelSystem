package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;

/**
 * The CRUD Repository Interface to store and retrieve all CustomerHours
 * objects.
 * 
 * @author Antoine Phan (@notkaramel)
 */
public interface CustomHoursRepository extends CrudRepository<CustomHours, Integer> {
    /**
     * Find a CustomHours object by its unique Id.
     * @param customHoursId
     * @return the corresponding CustomHours object
     */
    public CustomHours findCustomHoursByCustomHoursId(int customHoursId);

    /**
     * Find a CustomHours object by its date.
     * 
     * @param date
     * @return the corresponding CustomHours object
     */
    public CustomHours findCustomHoursByDate(Date date);
}
