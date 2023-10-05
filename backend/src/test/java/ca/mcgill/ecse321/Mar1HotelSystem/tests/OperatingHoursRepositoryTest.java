package ca.mcgill.ecse321.Mar1HotelSystem.tests;
/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.OperatingHoursRepository;

@ SpringBootTest
public class OperatingHoursRepositoryTest {
    //Setting up the operating hours repository
    @Autowired
    private OperatingHoursRepository operatingHoursRepository;

    //Clearing the database after the test
    @AfterEach
    public void clearDatabase() {
        operatingHoursRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadOperatingHours () {
        // Creating the operating hours
        OperatingHours.DayOfWeek dayOfWeek = OperatingHours.DayOfWeek.Monday;
        int openingHours = 1;
        int closingHours = 2;
        OperatingHours operatingHours = new OperatingHours(dayOfWeek, openingHours, closingHours);

        //Adding the customer to the persistence layer
        operatingHoursRepository.save(operatingHours);

        //Read from the database
        operatingHours = operatingHoursRepository.getOperatingHours();
    }
}

*/