package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.CustomHoursRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelScheduleRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.OperatingHoursRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.HotelScheduleRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
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
    public OperatingHours updateOperatingHoursByDay(DayOfWeek day, int openingHour, int closingHour) {
        OperatingHours toBeChangedOH = this.getOperatingHoursByDay(day);
        toBeChangedOH.setOpeningHour(openingHour);
        toBeChangedOH.setClosingHour(closingHour);
        operatingHoursRepository.save(toBeChangedOH);
        return toBeChangedOH;
    }

    @Transactional
    public OperatingHours getOperatingHoursByDay(DayOfWeek day) {
        OperatingHours oh = operatingHoursRepository.findOperatingHoursByDay(day);
        if (oh == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Could not find OperatingHours on " + day + ".");
        }
        return oh;
    }

    @Transactional
    public OperatingHours deleteOperatingHoursByDay(DayOfWeek day) {
        OperatingHours toBeDeletedOH = this.getOperatingHoursByDay(day);
        operatingHoursRepository.delete(toBeDeletedOH);
        return toBeDeletedOH;
    }

    @Transactional
    public OperatingHours getOperatingHoursById(int id) {
        OperatingHours oh = operatingHoursRepository.findOperatingHoursByOperatingHoursId(id);
        if (oh == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Could not find OperatingHours of id " + id + ".");
        }
        return oh;
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
    public CustomHours getCustomHoursByDate(Date date) {
        // Note that getting CustomHours by date is more complicated for the Controller
        // to handle, so we suggest using `getCustomHoursById` instead
        CustomHours ch = customHoursRepository.findCustomHoursByDate(date);
        if (ch == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Could not find CustomHours on " + date + ".");
        }
        return ch;
    }

    @Transactional
    public CustomHours getCustomHoursById(int id) {
        CustomHours ch = customHoursRepository.findCustomHoursByCustomHoursId(id);
        if (ch == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Could not find CustomHours of id " + id + ".");
        }
        return ch;
    }

    @Transactional
    public CustomHours updateCustomHoursByDate(Date date, int openingHour, int closingHour) {
        CustomHours toBeChangedCH = this.getCustomHoursByDate(date);
        toBeChangedCH.setOpeningHour(openingHour);
        toBeChangedCH.setClosingHour(closingHour);
        customHoursRepository.save(toBeChangedCH);
        return toBeChangedCH;
    }

    @Transactional
    public CustomHours updateCustomHoursById(int id, int openingHour, int closingHour) {
        CustomHours toBeChangedCH = this.getCustomHoursById(id);
        toBeChangedCH.setOpeningHour(openingHour);
        toBeChangedCH.setClosingHour(closingHour);
        customHoursRepository.save(toBeChangedCH);
        return toBeChangedCH;
    }

    @Transactional
    public List<CustomHours> getAllCustomHours() {
        return ServiceUtils.toList(customHoursRepository.findAll());
    }

    @Transactional
    public CustomHours deleteCustomHoursByDate(Date date) {
        // Similar warning as `getCustomHoursByDate`
        CustomHours toBeDeletedCH = this.getCustomHoursByDate(date);
        customHoursRepository.delete(toBeDeletedCH);
        return toBeDeletedCH;
    }

    @Transactional
    public CustomHours deleteCustomHoursById(int id) {
        CustomHours toBeDeletedCH = this.getCustomHoursById(id);
        customHoursRepository.delete(toBeDeletedCH);
        return toBeDeletedCH;
    }

    // Hotel Schedule Service Methods
    @Transactional
    public HotelSchedule createHotelSchedule(HotelScheduleRequestDto hotelScheduleRequestDto) {
        int year = hotelScheduleRequestDto.getYear();

        // Find all OperatingHours and CustomHours in the database, based on the list of
        // Ids in request
        List<CustomHours> customHoursList = new ArrayList<>();
        for (int chId : hotelScheduleRequestDto.getCustomHoursIdList()) {
            CustomHours ch = this.getCustomHoursById(chId);
            customHoursList.add(ch);
        }

        List<OperatingHours> operatingHoursList = new ArrayList<>();
        for (int ohId : hotelScheduleRequestDto.getOperatingHoursIdList()) {
            OperatingHours oh = this.getOperatingHoursById(ohId); 
            operatingHoursList.add(oh);
        }
        
        HotelSchedule newHS = new HotelSchedule(year, operatingHoursList, customHoursList);        hotelScheduleRepository.save(newHS);
        return newHS;
    }

    @Transactional
    public HotelSchedule getHotelScheduleByYear(int year) {
        HotelSchedule hs = hotelScheduleRepository.findHotelScheduleByYear(year);
        if (hs == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND,
                    "Could not find HotelSchedule of year " + year + ".");
        }
        return hs;
    }

    @Transactional
    public List<HotelSchedule> getAllHotelSchedules() {
        return ServiceUtils.toList(hotelScheduleRepository.findAll());
    }

    @Transactional
    public HotelSchedule deleteHotelSchedule(int year) {
        HotelSchedule deletedHS = this.getHotelScheduleByYear(year);
        try {
            hotelScheduleRepository.delete(deletedHS);
        } catch (Exception e) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST,
                    "Could not delete HotelSchedule of year " + year + ".");
        }
        return deletedHS;
    }

}
