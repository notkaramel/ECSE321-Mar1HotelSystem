package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.ShiftRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Shift;

@SpringBootTest
public class ShiftRepositoryTest {
    /**
     * This test is for the Shift class
     * 
     * @author Lucas Pacicco (@Lucaspac5)
     * 
     */
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    // Clear database before and after test
    @BeforeEach
    @AfterEach
    /*
     * Deletion order: from parent to child
     * shift -> employee
     */
    public void clearDatabase() {
        shiftRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadShift() {
        clearDatabase();
        // Create employee for shift
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@gmail.com";
        int phoneNumber = 111333333;
        String password = "abc";
        int hoursWorked = 7;
        Employee employee = new Employee(firstName, lastName, email, phoneNumber, password, hoursWorked);

        // Save employee
        employeeRepository.save(employee);

        // Create shift
        Date date = new Date();
        int startTime = 1;
        int endTime = 8;
        Shift shift = new Shift(employee, date, startTime, endTime);

        // Save shift
        shiftRepository.save(shift);

        // Read shift from database.
        int shiftId = shift.getShiftId();
        shift = shiftRepository.findShiftByShiftId(shiftId);

        // Assert that shift is not null and has correct attributes.
        assertNotNull(shift);
        assertEquals(employee.getEmail(), shift.getEmployee().getEmail());
        assertEquals(date, shift.getDate());
        assertEquals(startTime, shift.getStartTime());
        assertEquals(endTime, shift.getEndTime());
        clearDatabase();

    }

}
