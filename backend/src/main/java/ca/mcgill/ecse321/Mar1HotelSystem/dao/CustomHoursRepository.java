package ca.mcgill.ecse321.Mar1HotelSystem.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;

public interface CustomHoursRepository extends CrudRepository<CustomHours, String>{
    CustomHours findCustomHoursByDate(String date);
}
