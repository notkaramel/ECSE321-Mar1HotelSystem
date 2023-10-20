package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.CustomHoursRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelScheduleRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.OperatingHoursRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;
import jakarta.transaction.Transactional;

/**
 * All service methods for the Schedule features
 * Classes in order below:
 * - OperatingHours
 * - CustomHours
 * - HotelSchedule
 * Methods in order:
 * - createOperatingHours
 * - updateOperatingHours
 * - getOperatingHoursByDay
 * - getOperatingHoursByOpeningHour
 * - getAllOperatingHours
 * -------------------------------
 * - createCustomHours
 * - updateCustomHours
 * - getCustomHoursByDate
 * - getAllCustomHours
 * -------------------------------
 * - createHotelSchedule
 * - getAllHotelSchedules
 * - getHotelScheduleByYear
 * 
 * @author Antoine Phan (@notkaramel)
 */

@Service
public class ScheduleService {
    @Autowired
    OperatingHoursRepository operatingHoursRepository;

    @Autowired
    CustomHoursRepository customHoursRepository;

    @Autowired
    HotelScheduleRepository hotelScheduleRepository;

    // Operating Hours Service Methods
    @Transactional
    public OperatingHours createOperatingHours(DayOfWeek day, int openingHour, int closingHour) {
        OperatingHours newOH = new OperatingHours(day, openingHour, closingHour);
        operatingHoursRepository.save(newOH);
        return newOH;
    }

    @Transactional
    public OperatingHours updateOperatingHours(DayOfWeek day, int openingHour, int closingHour) {
        OperatingHours toBeChangedOH = operatingHoursRepository.findOperatingHoursByDay(day);
        toBeChangedOH.setOpeningHour(openingHour);
        toBeChangedOH.setClosingHour(closingHour);
        operatingHoursRepository.save(toBeChangedOH);
        return toBeChangedOH;
    }

    @Transactional
    public OperatingHours getOperatingHoursByDay(DayOfWeek day) {
        return operatingHoursRepository.findOperatingHoursByDay(day);
    }

    @Transactional
    public OperatingHours getOperatingHoursByOpeningHour(int openingHour) {
        return operatingHoursRepository.findOperatingHoursByOpeningHour(openingHour);
    }

    @Transactional
    public List<OperatingHours> getAllOperatingHours() {
        return ServiceUtils.toList(operatingHoursRepository.findAll());
    }

    // Custom Hours Service Methods
    @Transactional
    public CustomHours createCustomHours(Date date, int openingHour, int closingHour) {
        CustomHours newCH = new CustomHours(date, openingHour, closingHour);
        customHoursRepository.save(newCH);
        return newCH;
    }

    @Transactional
    public CustomHours updateCustomHours(Date date, int openingHour, int closingHour) {
        CustomHours toBeChangedCH = customHoursRepository.findCustomHoursByDate(date);
        toBeChangedCH.setOpeningHour(openingHour);
        toBeChangedCH.setClosingHour(closingHour);
        customHoursRepository.save(toBeChangedCH);
        return toBeChangedCH;
    }

    @Transactional
    public CustomHours getCustomHoursByDate(Date date) {
        return customHoursRepository.findCustomHoursByDate(date);
    }

    @Transactional
    public List<CustomHours> getAllCustomHours() {
        return ServiceUtils.toList(customHoursRepository.findAll());
    }

    // Hotel Schedule Service Methods
    @Transactional
    public HotelSchedule createHotelSchedule(int year,
            OperatingHours[] operatingHoursList, CustomHours[] customHoursList) {
        HotelSchedule newHS = new HotelSchedule();
        newHS.setYear(year);
        newHS.setOperatingHours(operatingHoursList);
        newHS.setCustomHours(customHoursList);
        hotelScheduleRepository.save(newHS);
        return newHS;
    }

    @Transactional
    public List<HotelSchedule> getAllHotelSchedules() {
        return ServiceUtils.toList(hotelScheduleRepository.findAll());
    }

    @Transactional
    public HotelSchedule getHotelScheduleByYear(int year) {
        return hotelScheduleRepository.findHotelScheduleByYear(year);
    }

    @Transactional
    public boolean deleteHotelSchedule(int year) {
        HotelSchedule toBeDeletedHS = hotelScheduleRepository.findHotelScheduleByYear(year);
        if (toBeDeletedHS == null) {
            return false;
        }
        hotelScheduleRepository.delete(toBeDeletedHS);
        return true;
    }

}
