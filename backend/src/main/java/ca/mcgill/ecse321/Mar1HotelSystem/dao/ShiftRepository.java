package ca.mcgill.ecse321.Mar1HotelSystem.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Shift;

public interface ShiftRepository extends CrudRepository<Shift, String>{
    public Shift findShiftByShiftId(int shiftId);
}
