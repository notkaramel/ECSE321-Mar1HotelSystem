package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;

public interface HotelScheduleRepository extends CrudRepository<HotelSchedule, String> {
    HotelSchedule getHotelSchedule();
}
