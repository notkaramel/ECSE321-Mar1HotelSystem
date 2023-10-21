package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;

/**
 * The CRUD Repository Interface to store and retrieve all OperatingHours
 * objects.
 * 
 * @author Antoine Phan (@notkaramel)
 */
public interface OperatingHoursRepository extends CrudRepository<OperatingHours, Integer> {
    /**
     * Find an OperatingHours object by its operatingHoursId.
     * 
     * @param operatingHoursId
     * @return the corresponding OperatingHours object
     */
    public OperatingHours findOperatingHoursByOperatingHoursId(int operatingHoursId);

    /**
     * Find an OperatingHours object by its DayOfWeek
     * 
     * @param day
     * @return the corresponding OperatingHours object
     */
    public OperatingHours findOperatingHoursByDay(OperatingHours.DayOfWeek day);
}
