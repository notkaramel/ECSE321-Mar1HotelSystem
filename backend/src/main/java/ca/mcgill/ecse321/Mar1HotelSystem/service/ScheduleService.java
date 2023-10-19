package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.CustomHoursRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelScheduleRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.OperatingHoursRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;

/**
 * All service methods for the Schedule features
 */

@Service
public class ScheduleService {
    @Autowired
    OperatingHoursRepository operatingHoursRepository;

    @Autowired
    CustomHoursRepository customHoursRepository;

    @Autowired
    HotelScheduleRepository hotelScheduleRepository;

    public List<OperatingHours> getAllOperatingHours() {
        return ServiceUtils.toList(operatingHoursRepository.findAll());
    }

    public List<CustomHours> getAllCustomHours() {
        return ServiceUtils.toList(customHoursRepository.findAll());
    }

}
