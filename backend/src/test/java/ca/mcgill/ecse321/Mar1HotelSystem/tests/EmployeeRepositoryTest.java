package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;

/**
 * This test is for the Employee class
 *
 * @author Lucas Pacicco (@Lucaspac5)
 * @author Adam Corbier (@Ad2Am2)
 *
 */
@SpringBootTest
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    // Clear database before and after test
    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        employeeRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadEmployee() {

        clearDatabase();
        // Create Employee
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@gmail.com";
        int phoneNumber = 111333333;
        String password = "abc";
        int hoursWorked = 8;
        Employee employee = new Employee(firstName, lastName, email, phoneNumber, password, hoursWorked);

        // Save employee
        employeeRepository.save(employee);

        // Read employee from database.
        employee = employeeRepository.findEmployeeByEmail(email);

        // Assert that employee is not null and has correct attributes.
        assertNotNull(employee);
        assertEquals(firstName, employee.getFirstName());
        assertEquals(lastName, employee.getLastName());
        assertEquals(email, employee.getEmail());
        assertEquals(phoneNumber, employee.getPhoneNumber());
        assertEquals(password, employee.getPassword());
        clearDatabase();
    }

}
