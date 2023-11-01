package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

/**
 * Service tests for the Employee class.
 *
 * @author Liu, ZiXu
 */
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeDao;

    @InjectMocks
    private EmployeeService employeeService;

    private static final String EMPLOYEE_KEY = "TestEmployee@mail.mcgill.ca";
    private static final String NONEXISTING_KEY = "NotAnEmployee";

    /**
     * Set mock employee output before each test.
     */
    @BeforeEach
    public void setMockOutput() {
        lenient().when(employeeDao.findEmployeeByEmail(anyString())).thenAnswer((invocation) -> {
            if (invocation.getArgument(0).equals(EMPLOYEE_KEY)) {
                return new Employee(
                        "TestFirstName",
                        "TestLastName",
                        EMPLOYEE_KEY,
                        1234567890,
                        "TestPassword",
                        0
                );
            } else {
                return null;
            }
        });
        lenient().when(employeeDao.findAll()).thenAnswer((invocation) -> {
            ArrayList<Employee> employees = new ArrayList<>();
            Employee employee = new Employee(
                    "TestFirstName",
                    "TestLastName",
                    EMPLOYEE_KEY,
                    1234567890,
                    "TestPassword",
                    0
            );
            employees.add(employee);
            return employees;
        });
    }

    /**
     * Test creating an employee.
     */
    @Test
    public void testCreateEmployee() {
        //assertEquals(0, employeeService.getAllEmployees().size());
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    "Dolan",
                    "Duck",
                    EMPLOYEE_KEY,
                    1234567891,
                    "password123",
                    0
            );
        } catch (IllegalArgumentException e) {
            fail();
        }
        // Check not null
        assertNotNull(employee);
        assertEquals(EMPLOYEE_KEY, employee.getEmail());
    }

    /**
     * Test creating an employee with nulls.
     */
    @Test
    public void testCreateEmployeeNull() {
        //assertEquals(0, employeeService.getAllEmployees().size());
        String error = null;
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    null,
                    null,
                    null,
                    1234567891,
                    null,
                    0
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(employee);
        // check error
        assertEquals("The first name cannot be empty!", error);
    }

    /**
     * Test creating an employee with an empty string.
     */
    @Test
    public void testCreateEmployeeEmpty() {
        //assertEquals(0, employeeService.getAllEmployees().size());
        String error = null;
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    "",
                    "",
                    "",
                    1234567891,
                    "",
                    0
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(employee);
        // check error
        assertEquals("The first name cannot be empty!", error);
    }

    /**
     * Test creating an employee with a first name that is an empty string.
     */
    @Test
    public void testCreateEmployeeFirstNameSpaces() {
        //assertEquals(0, employeeService.getAllEmployees().size());
        String error = null;
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    " ",
                    "doot",
                    EMPLOYEE_KEY,
                    1234567891,
                    " ",
                    0
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(employee);
        // check error
        assertEquals("The first name cannot be empty!", error);
    }

    /**
     * Test creating an employee with a last name that is an empty string.
     */
    @Test
    public void testCreateEmployeeLastNameSpaces() {
        //assertEquals(0, employeeService.getAllEmployees().size());
        String error = null;
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    "boi",
                    "",
                    EMPLOYEE_KEY,
                    1234567891,
                    " ",
                    0
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(employee);
        // check error
        assertEquals("The last name cannot be empty!", error);
    }

    /**
     * Test creating an employee with an email that is an empty string.
     */
    @Test
    public void testCreateEmployeeEmailSpaces() {
        //assertEquals(0, employeeService.getAllEmployees().size());
        String error = null;
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    "boi",
                    "boi",
                    "",
                    1234567891,
                    " ",
                    0
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(employee);
        // check error
        assertEquals("The email cannot be empty!", error);
    }

    /**
     * Test creating an employee with an email without "@".
     */
    @Test
    public void testCreateEmployeeEmailNoA() {
        //assertEquals(0, employeeService.getAllEmployees().size());
        String error = null;
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    "boi",
                    "boi",
                    "boi",
                    1234567891,
                    " ",
                    0
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(employee);
        // check error
        assertEquals("The email is invalid!", error);
    }

    /**
     * Test creating an employee with an email without ".".
     */
    @Test
    public void testCreateEmployeeEmailNoPeriod() {
        //assertEquals(0, employeeService.getAllEmployees().size());
        String error = null;
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    "boi",
                    "boi",
                    "boi@boi",
                    1234567891,
                    " ",
                    0
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(employee);
        // check error
        assertEquals("The email is invalid!", error);
    }

    /**
     * Test creating an employee with a password that is an empty string.
     */
    @Test
    public void testCreateEmployeePasswordSpaces() {
        //assertEquals(0, employeeService.getAllEmployees().size());
        String error = null;
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    "boi",
                    "boi",
                    EMPLOYEE_KEY,
                    1234567891,
                    "",
                    0
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(employee);
        // check error
        assertEquals("The password cannot be empty!", error);
    }

    /**
     * Test retrieving an employee.
     */
    @Test
    public void testGetExistingEmployee() {
        assertEquals(EMPLOYEE_KEY, employeeService.getEmployee(EMPLOYEE_KEY).getEmail());
    }

    /**
     * Test retrieving a non-existing customer.
     */
    @Test
    public void testGetNonExistingEmployee() {
        assertNull(employeeService.getEmployee(NONEXISTING_KEY));
    }

    @Test
    public void testUpdateEmployeeFirstName() {
    	String newFirstName = "Dolan";
    	Employee employee = new Employee();
        boolean checkFirstName = false;
    	try {
            employee = employeeService.createEmployee(
                    "boi",
                    "boi2",
                    "pain@gmail.com",
                    1234567891,
                    "doot",
                    0
            );
    		checkFirstName = employeeService.updateEmployeeFirstName(newFirstName, "pain@gmail.com");
    	} catch (IllegalArgumentException e) {
    		fail();
    	}
    	// Check not null
    	assertTrue(checkFirstName);
    	assertEquals(newFirstName, employee.getFirstName());
    }
}
