package ca.mcgill.ecse321.Mar1HotelSystem.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;

public interface OperatingHoursRepository extends CrudRepository<OperatingHours, Integer>{
    public OperatingHours findOperatingHoursByOperatingHoursId(int operatingHoursId);
    public OperatingHours findOperatingHoursByOpeningHour(int openingHour);
}
