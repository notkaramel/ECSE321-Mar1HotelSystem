package ca.mcgill.ecse321.Mar1HotelSystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;
import jakarta.transaction.Transactional;

/**
 * This is the test class for the HotelSchedule repository.
 *
 * @author Mokhtari, Bilar
 */
@SpringBootTest
public class HotelScheduleRepositoryTest {
    @Autowired
    private HotelScheduleRepository hotelScheduleRepository;

    @Autowired
    private CustomHoursRepository customHoursRepository;

    @Autowired
    private OperatingHoursRepository operatingHoursRepository;

    @BeforeEach
    @AfterEach
    // Deleting in this particular order from Parent to Child in order to maintain
    // association integrity
    public void clearDatabase() {
        hotelScheduleRepository.deleteAll();
        operatingHoursRepository.deleteAll();
        customHoursRepository.deleteAll();
    }

    // ------------------
    // Test Methods
    // ------------------
    @Test
    @Transactional
    public void testPersistAndLoadHotelSchedule() {
        // Create and Save Hotel Object (Required for Room, which is in turn required
        // for Booking)
        Date date = new Date();
        CustomHours customHours = new CustomHours(date, 8, 20);
        OperatingHours operatingHours = new OperatingHours(DayOfWeek.Monday, 8, 20);
        CustomHours[] customHoursArray = new CustomHours[1];
        OperatingHours[] operatingHoursArray = new OperatingHours[1];
        customHoursRepository.save(customHours);
        customHours = customHoursRepository.findCustomHoursByDate(date);
        operatingHoursRepository.save(operatingHours);
        operatingHours = operatingHoursRepository.findOperatingHoursByOperatingHoursId(operatingHours.getOperatingHoursId());

        customHoursArray[0] = customHours;
        operatingHoursArray[0] = operatingHours;
        HotelSchedule hotelSchedule = new HotelSchedule(2023, operatingHoursArray, customHoursArray);

        // ------------------
        // Save HotelSchedule
        // ------------------
        hotelScheduleRepository.save(hotelSchedule);
        hotelSchedule = hotelScheduleRepository.findHotelScheduleByYear(2023);

        // ------------------
        // Assertions
        // ------------------

        // Assert the HotelSchedule
        assertNotNull(hotelSchedule);
        assertEquals(2023, hotelSchedule.getYear());

        // Ensure the size of the retrieved customHours and operatingHours lists
        assertEquals(1, hotelSchedule.getCustomHours().size());
        assertEquals(1, hotelSchedule.getOperatingHours().size());

        // Validate CustomHours details
        CustomHours retrievedCustomHour = hotelSchedule.getCustomHours().get(0);
        assertEquals(date, retrievedCustomHour.getDate());

        // Validate OperatingHours details
        OperatingHours retrievedOperatingHour = hotelSchedule.getOperatingHours().get(0);
        assertEquals(DayOfWeek.Monday, retrievedOperatingHour.getDayOfWeek());

    }
}
