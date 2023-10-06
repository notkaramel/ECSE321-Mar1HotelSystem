package ca.mcgill.ecse321.Mar1HotelSystem.dao;
import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;

public interface CustomHoursRepository extends CrudRepository<CustomHours, String>{
    public CustomHours findCustomHoursByDate(Date date);
}
