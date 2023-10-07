package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Shift;

/**
 * The CRUD Repository Interface to store and retrieve all Shift objects.
 *
 * @author Antoine Phan (@notkaramel)
 */
public interface ShiftRepository extends CrudRepository<Shift, String> {
    /**
     * Find a Shift object by their shiftId.
     *
     * @param shiftId
     * @return the Shift with the shiftId
     */

    public Shift findShiftByShiftId(int shiftId);
}
