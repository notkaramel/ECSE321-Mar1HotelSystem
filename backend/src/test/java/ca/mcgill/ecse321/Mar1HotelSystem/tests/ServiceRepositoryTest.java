package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Service;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.ServiceRepository;

public class ServiceRepositoryTest {
    // Setting up the service repository
    @Autowired
    private ServiceRepository serviceRepository;

    // Clearing the database after the test
    @AfterEach
    public void clearDatabase() {
        serviceRepository.deleteAll();
    }

    @Test
    public void testPersistAndReadService() {
        // Creating an employee
        String firstName = "Candice";
        String lastName = "Evergreen";
        String email = "candice@gmail.com";
        int phoneNumber = 438;
        String password = "BOI100";
        int hoursWorked = 10;
        Employee employee = new Employee(firstName, lastName, email, phoneNumber, password, hoursWorked);

        // Creating a request
        String description = "Need some towels";


    }

}