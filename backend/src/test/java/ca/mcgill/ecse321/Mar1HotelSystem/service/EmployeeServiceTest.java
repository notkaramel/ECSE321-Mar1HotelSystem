package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeDao;

    @InjectMocks
    private EmployeeService employeeService;

    private static final String EMPLOYEE_KEY = "TestEmployee";
    private static final String NONEXISTING_KEY = "NotAnEmployee";

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

    @Test
    public void testCreateEmployee() {
        assertEquals(0, employeeService.getAllEmployees().size());
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

    @Test
    public void testCreateEmployeeNull() {
        assertEquals(0, employeeService.getAllEmployees().size());
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
        assertEquals("Person's first name cannot be empty!", error);
    }

    @Test
    public void testCreateEmployeeEmpty() {
        assertEquals(0, employeeService.getAllEmployees().size());
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
        assertEquals("Person's first name cannot be empty!", error);
    }

    @Test
    public void testCreateEmployeeFirstNameSpaces() {
        assertEquals(0, employeeService.getAllEmployees().size());
        String error = null;
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    " ",
                    "doot",
                    "doot@gmail.com",
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
        assertEquals("Person's first name cannot be empty!", error);
    }

    @Test
    public void testCreateEmployeeLastNameSpaces() {
        assertEquals(0, employeeService.getAllEmployees().size());
        String error = null;
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    "boi",
                    "",
                    "boi@boi.com",
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
        assertEquals("Person's last name cannot be empty!", error);
    }

    @Test
    public void testCreateEmployeeEmailSpaces() {
        assertEquals(0, employeeService.getAllEmployees().size());
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
        assertEquals("Person's email cannot be empty!", error);
    }

    @Test
    public void testCreateEmployeeEmailNoA() {
        assertEquals(0, employeeService.getAllEmployees().size());
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
        assertEquals("Person's email is not valid!", error);
    }

    @Test
    public void testCreateEmployeeEmailNoPeriod() {
        assertEquals(0, employeeService.getAllEmployees().size());
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
        assertEquals("Person's email is not valid!", error);
    }

    @Test
    public void testCreateEmployeePasswordSpaces() {
        assertEquals(0, employeeService.getAllEmployees().size());
        String error = null;
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    "boi",
                    "boi",
                    "boi@gmail.com",
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
        assertEquals("Person's password cannot be empty!", error);
    }


}
