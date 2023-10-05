package ca.mcgill.ecse321.Mar1HotelSystem.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;

public interface OperatingHoursRepository extends CrudRepository<OperatingHours, String>{
    public OperatingHours getOperatingHours();
}
