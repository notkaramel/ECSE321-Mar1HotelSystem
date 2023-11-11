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
        OperatingHours toBeChangedOH = operatingHoursRepository.findOperatingHoursByDay(day);
        if (toBeChangedOH == null){
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Could not find OperatingHours on " + day + ".");
        }
        toBeChangedOH.setOpeningHour(openingHour);
        toBeChangedOH.setClosingHour(closingHour);
        operatingHoursRepository.save(toBeChangedOH);
        return toBeChangedOH;
    }

    @Transactional
    public OperatingHours getOperatingHoursByDay(DayOfWeek day) {
        OperatingHours oh = operatingHoursRepository.findOperatingHoursByDay(day);
        if (oh == null){
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Could not find OperatingHours on " + day + ".");
        }
        return oh;
    }

    @Transactional
    public OperatingHours getOperatingHoursById(int id) {
        OperatingHours oh = operatingHoursRepository.findOperatingHoursByOperatingHoursId(id);
        if (oh == null){
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
    public CustomHours getCustomHoursByDate(Date date) {
        CustomHours ch = customHoursRepository.findCustomHoursByDate(date);
        if (ch == null){
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Could not find CustomHours on " + date + ".");
        }
        return ch;
    }

    @Transactional
    public CustomHours getCustomHoursById(int id) {
        CustomHours ch = customHoursRepository.findCustomHoursByCustomHoursId(id);
        if (ch == null){
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Could not find CustomHours of id " + id + ".");
        }
        return ch;
    }

    @Transactional
    public List<CustomHours> getAllCustomHours() {
        return ServiceUtils.toList(customHoursRepository.findAll());
    }

    @Transactional
    public CustomHours deleteCustomHoursByDate(Date date) {
        CustomHours toBeDeletedCH = customHoursRepository.findCustomHoursByDate(date);
        if (toBeDeletedCH == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Could not find CustomHours on " + date + ".");
        }
        customHoursRepository.delete(toBeDeletedCH);
        return toBeDeletedCH;
    }

    // Hotel Schedule Service Methods
    @Transactional
    public HotelSchedule createHotelSchedule(HotelScheduleRequestDto request) {
        HotelSchedule newHS = new HotelSchedule();
        newHS.setYear(request.getYear());

        // Find all OperatingHours and CustomHours in the database, based on the list of Ids in request
        List<CustomHours> customHoursList = new ArrayList<>();
        for (int chId : request.getCustomHoursIdList()) {
            CustomHours ch = customHoursRepository.findCustomHoursByCustomHoursId(chId);
            if (ch == null) {
                throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Could not find CustomHours of id " + chId + ".");
            }
            customHoursList.add(ch);
        }

        List<OperatingHours> operatingHoursList = new ArrayList<>();
        for (int ohId : request.getOperatingHoursIdList()) {
            OperatingHours oh = operatingHoursRepository.findOperatingHoursByOperatingHoursId(ohId);
            if (oh == null) {
                throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Could not find OperatingHours of id " + ohId + ".");
            }
            operatingHoursList.add(oh);
        }
        newHS.setOperatingHours(operatingHoursList);
        newHS.setCustomHours(customHoursList);

        hotelScheduleRepository.save(newHS);
        return newHS;
    }

    @Transactional
    public HotelSchedule getHotelScheduleByYear(int year) {
        return hotelScheduleRepository.findHotelScheduleByYear(year);
    }

    @Transactional
    public List<HotelSchedule> getAllHotelSchedules() {
        return ServiceUtils.toList(hotelScheduleRepository.findAll());
    }

    @Transactional
    public HotelSchedule deleteHotelSchedule(int year) {
        HotelSchedule deletedHS = hotelScheduleRepository.findHotelScheduleByYear(year);
        if (deletedHS == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Could not find HotelSchedule of year " + year + ".");
        }
        try {
            hotelScheduleRepository.delete(deletedHS);
        } catch (Exception e) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Could not delete HotelSchedule of year " + year + ".");
        }
        return deletedHS;
    }

}
