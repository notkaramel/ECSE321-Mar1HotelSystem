package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.*;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Service tests for the Employee class.
 *
 * @author Liu, ZiXu
 */
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeDao;
    @Mock
    private CustomerRepository customerDao;
    @Mock
    private AccountRepository accountDao;
    @Mock
    private ManagerRepository managerDao;
    @Mock
    private GeneralUserRepository generalUserDao;
    @InjectMocks
    private EmployeeService employeeService;
    private static final String EMPLOYEE_KEY = "TestEmployee@mail.mcgill.ca";
    private static final String NONEXISTING_KEY = "NotAnEmployee@mail.com";
    private static final String EMPLOYEE_INITIAL_KEY_1 = "testemployee1@mail.com";
    private static final String EMPLOYEE_INITIAL_KEY_2 = "testemployee2@mail.com";

    /**
     * Set mock employee output before each test.
     */
    @BeforeEach
    public void setMockOutput() {
        lenient().when(employeeDao.findEmployeeByEmail(anyString())).thenAnswer((invocation) -> {
            if (invocation.getArgument(0).equals(EMPLOYEE_INITIAL_KEY_1)) {
                return new Employee(
                        "TestFirstName",
                        "TestLastName",
                        EMPLOYEE_INITIAL_KEY_1,
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
            Employee employee1 = new Employee(
                    "TestFirstName",
                    "TestLastName",
                    EMPLOYEE_INITIAL_KEY_1,
                    1234567890,
                    "TestPassword",
                    0
            );
            employees.add(employee1);
            Employee employee2 = new Employee(
                    "TestFirstName2",
                    "TestLastName2",
                    EMPLOYEE_INITIAL_KEY_2,
                    1234567890,
                    "TestPassword2",
                    0
            );
            employees.add(employee2);
            return employees;
        });
        Answer<?> returnParameterAsAnswer =
                (InvocationOnMock invocation) -> {
                    return invocation.getArgument(0);
                };
        lenient().when(employeeDao.save(any(Employee.class))).thenAnswer(returnParameterAsAnswer);
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
        } catch (Mar1HotelSystemException e) {
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
        } catch (Mar1HotelSystemException e) {
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
        } catch (Mar1HotelSystemException e) {
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
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(employee);
        // check error
        assertEquals("The first name cannot be empty!", error);
    }

    /**
     * Test creating an employee with a last name that is null.
     */
    @Test
    public void testCreateEmployeeLastNameNull() {
        String error = null;
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    "boi",
                    null,
                    EMPLOYEE_KEY,
                    1234567891,
                    " 1",
                    0
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(employee);
        // check error
        assertEquals("The last name cannot be empty!", error);
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
                    " 1",
                    0
            );
        } catch (Mar1HotelSystemException e) {
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
                    " 1",
                    0
            );
        } catch (Mar1HotelSystemException e) {
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
                    " 1",
                    0
            );
        } catch (Mar1HotelSystemException e) {
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
                    " 1",
                    0
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(employee);
        // check error
        assertEquals("The email is invalid!", error);
    }

    /**
     * Test creating an employee with a negative phone number.
     */
    @Test
    public void testCreateEmployeeInvalidPhoneNumber() {
        String error = null;
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    "boi",
                    "boi",
                    EMPLOYEE_KEY,
                    -1,
                    " 1",
                    0
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(employee);
        // check error
        assertEquals("The phone number must be above 0!", error);
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
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(employee);
        // check error
        assertEquals("The password cannot be empty!", error);
    }

    /**
     * Test creating an employee with an existing email
     */
    @Test
    public void testCreateEmployeeExistingEmail() {
        String error = null;
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    "boi",
                    "boi",
                    EMPLOYEE_INITIAL_KEY_1,
                    1234567891,
                    " 1",
                    0
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(employee);
        // check error
        assertEquals("User using that email already exists!", error);
    }

    /**
     * Test retrieving an employee.
     */
    @Test
    public void testGetExistingEmployee() {
        assertEquals(EMPLOYEE_INITIAL_KEY_1, employeeService.getEmployee(EMPLOYEE_INITIAL_KEY_1).getEmail());
    }

    /**
     * Test retrieving a non-existing customer.
     */
    @Test
    public void testGetNonExistingEmployee() {
        assertNull(employeeService.getEmployee(NONEXISTING_KEY));
    }

    /**
     * Test retrieving all employees.
     */
    @Test
    public void testGetAllEmployees() {
        ArrayList<Employee> listOfEmployees = (ArrayList<Employee>) employeeService.getAllEmployees();
        assertEquals(2, listOfEmployees.size());
        for(Employee employee : listOfEmployees) {
            assertNotNull(employee);
        }
    }

    /**
     * Test updating an employee.
     */
    @Test
    public void testUpdateEmployee() {
    	String newFirstName = "Dolan";
        String newLastName = "Duck";
        String newPassword = "password123";
        long newPhoneNumber = 123;
    	Employee employee = null;
    	try {
    		employee = employeeService.updateEmployee(
                    newFirstName,
                    newLastName,
                    newPhoneNumber,
                    newPassword,
                    EMPLOYEE_INITIAL_KEY_1);
    	} catch (Mar1HotelSystemException e) {
    		fail();
    	}
    	// Check not null
        assertNotNull(employee);
    	assertEquals(newFirstName, employee.getFirstName());
        assertEquals(newLastName, employee.getLastName());
        assertEquals(newPhoneNumber, employee.getPhoneNumber());
        assertEquals(newPassword, employee.getPassword());
    }

    /**
     * Test updating an employee with an invalid first name.
     */
    @Test
    public void testUpdateEmployeeInvalidFirstName() {
        	String newFirstName = "";
            String newLastName = "Duck";
            long newPhoneNumber = 123;
            String newPassword = "password123";
        	Employee employee = null;
        	String error = null;
        	try {
        		employee = employeeService.updateEmployee(
                        newFirstName,
                        newLastName,
                        newPhoneNumber,
                        newPassword,
                        EMPLOYEE_INITIAL_KEY_1);
        	} catch (Mar1HotelSystemException e) {
        		error = e.getMessage();
        	}
        	// Check null
            assertNull(employee);
        	assertEquals("The first name cannot be empty!", error);
    }

    /**
     * Test updating an employee with an invalid last name.
     */
    @Test
    public void testUpdateEmployeeInvalidLastName() {
        	String newFirstName = "Dolan";
            String newLastName = "";
            long newPhoneNumber = 123;
            String newPassword = "password123";
        	Employee employee = null;
        	String error = null;
        	try {
        		employee = employeeService.updateEmployee(
                        newFirstName,
                        newLastName,
                        newPhoneNumber,
                        newPassword,
                        EMPLOYEE_INITIAL_KEY_1);
        	} catch (Mar1HotelSystemException e) {
        		error = e.getMessage();
        	}
        	// Check null
            assertNull(employee);
        	assertEquals("The last name cannot be empty!", error);
    }

    /**
     * Test updating an employee with a negative phone number.
     */
    @Test
    public void testUpdateEmployeeInvalidPhoneNumber() {
            String newFirstName = "Dolan";
            String newLastName = "Duck";
            long newPhoneNumber = -1;
            String newPassword = "password123";
            Employee employee = null;
            String error = null;
            try {
                employee = employeeService.updateEmployee(
                        newFirstName,
                        newLastName,
                        newPhoneNumber,
                        newPassword,
                        EMPLOYEE_INITIAL_KEY_1);
            } catch (Mar1HotelSystemException e) {
                error = e.getMessage();
            }
            // Check null
            assertNull(employee);
            assertEquals("The phone number must be above 0!", error);
    }

    /**
     * Test updating an employee with an empty email.
     */
    @Test
    public void testUpdateEmployeeSpaceEmail() {
        String newFirstName = "Dolan";
        String newLastName = "Duck";
        long newPhoneNumber = 123;
        String newPassword = "password123";
        Employee employee = null;
        String error = null;
        try {
            employee = employeeService.updateEmployee(
                    newFirstName,
                    newLastName,
                    newPhoneNumber,
                    newPassword,
                    "");
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(employee);
        assertEquals("The email cannot be empty!", error);
    }

    /**
     * Test updating an employee with an invalid email.
     */
    @Test
    public void testUpdateEmployeeInvalidEmail() {
        String newFirstName = "Dolan";
        String newLastName = "Duck";
        long newPhoneNumber = 123;
        String newPassword = "password123";
        Employee employee = null;
        String error = null;
        try {
            employee = employeeService.updateEmployee(
                    newFirstName,
                    newLastName,
                    newPhoneNumber,
                    newPassword,
                    "boimailcom");
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(employee);
        assertEquals("The email is invalid!", error);
    }

    /**
     * Test updating an employee with an invalid password.
     */
    @Test
    public void testUpdateEmployeeInvalidPassword() {
        String newFirstName = "Dolan";
        String newLastName = "Duck";
        long newPhoneNumber = 123;
        String newPassword = "";
        Employee employee = null;
        String error = null;
        try {
            employee = employeeService.updateEmployee(newFirstName,
                    newLastName,
                    newPhoneNumber,
                    newPassword,
                    EMPLOYEE_INITIAL_KEY_1);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(employee);
        assertEquals("The password cannot be empty!", error);
    }

    /**
     * Test updating an employee that isn't in the database.
     */
    @Test
    public void testUpdateEmployeeEmailNotFound() {
        	String newFirstName = "Dolan";
            String newLastName = "Duck";
            long newPhoneNumber = 123;
            String newPassword = "password123";
        	Employee employee = null;
        	String error = null;
        	try {
        		employee = employeeService.updateEmployee(
                        newFirstName,
                        newLastName,
                        newPhoneNumber,
                        newPassword,
                        NONEXISTING_KEY);
        	} catch (Mar1HotelSystemException e) {
        		error = e.getMessage();
        	}
        	// Check null
            assertNull(employee);
        	assertEquals("The employee does not exist!", error);
    }

    /**
     * Test deleting an employee.
     */
    @Test
    public void testDeleteEmployee() {
        boolean deleted = false;
        try {
            deleted = employeeService.deleteEmployee(EMPLOYEE_INITIAL_KEY_1);
        } catch (Exception e) {
            fail();
        }
        // Check if the employee was deleted
        assertTrue(deleted);
        verify(employeeDao, times(1)).delete(employeeService.getEmployee(EMPLOYEE_INITIAL_KEY_1));
    }

    /**
     * Test deleting an employee that isn't in the database.
     */
    @Test
    public void testDeleteEmployeeInvalidEmail() {
        boolean deleted = false;
        String error = null;
        try {
            deleted = employeeService.deleteEmployee(NONEXISTING_KEY);
        } catch (Exception e) {
            error = e.getMessage();
        }
        // Check if the employee was deleted
        assertFalse(deleted);
        assertEquals("The employee does not exist!", error);
    }
}
