package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.CustomHoursRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;
/**
 * This test class is for the CustomHoursRepository DAO.
 * 
 * @author Bilar Mokhtari (@bmokhtari)
 * @author Antoine Phan (@notkaramel)
 */
@SpringBootTest
public class CustomHoursRepositoryTest {

    @Autowired
    private CustomHoursRepository customHoursRepository;

    // ---------------------------
    // Setup and Teardown Methods
    // ---------------------------

    // Clears the database before and after each test
    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        customHoursRepository.deleteAll();
    }

    // ------------------
    // Test Methods
    // ------------------

    @Test
    public void testPersistAndLoadCustomHours() {
        // Create CustomHours object with current date and specific opening and closing hours
        Date date = new Date();
        int openingHour = 8;
        int closingHour = 23;
        CustomHours customHours = new CustomHours(date, openingHour, closingHour);

        // Save CustomHours to repository
        customHoursRepository.save(customHours);

        // Retrieve saved CustomHours using the date as search parameter
        customHours = customHoursRepository.findCustomHoursByDate(date);
        
        // ------------------
        // Assertions
        // ------------------
        assertNotNull(customHours);
        assertEquals(date, customHours.getDate());
        assertEquals(openingHour, customHours.getOpeningHour());
        assertEquals(closingHour, customHours.getClosingHour());
    }
}
