package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.*;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Shift;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    @Mock
    private ShiftRepository shiftDao;
    @InjectMocks
    private EmployeeService employeeService;
    private static final String EMPLOYEE_KEY = "TestEmployee@mail.mcgill.ca";
    private static final String NONEXISTING_KEY = "NotAnEmployee@mail.com";
    private static final String EMPLOYEE_INITIAL_KEY_1 = "testemployee1@mail.com";
    private static final String EMPLOYEE_INITIAL_KEY_2 = "testemployee2@mail.com";
    private static final int NONEXISTING_SHIFT_KEY = 10;
    private static final int SHIFT_KEY_INITIAL_1 = 1;
    private static final int SHIFT_KEY_INITIAL_2 = 2;

    /**
     * Set mock employee output before each test.
     */
    @BeforeEach
    public void setMockOutput() {
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
        lenient().when(employeeDao.findEmployeeByEmail(anyString())).thenAnswer((invocation) -> {
            if (invocation.getArgument(0).equals(EMPLOYEE_INITIAL_KEY_1)) {
                return employee1;
            } else {
                return null;
            }
        });
        lenient().when(employeeDao.findAll()).thenAnswer((invocation) -> employees);
        Answer<?> returnParameterAsAnswer =
                (InvocationOnMock invocation) -> invocation.getArgument(0);
        lenient().when(employeeDao.save(any(Employee.class))).thenAnswer(returnParameterAsAnswer);
        ArrayList<Shift> shifts = new ArrayList<>();
        Shift shift1 = new Shift(
                employee1,
                new Date(2000, Calendar.JANUARY,1),
                1,
                2
        );
        shift1.setShiftId(SHIFT_KEY_INITIAL_1);
        shifts.add(shift1);
        Shift shift2 = new Shift(
                employee2,
                new Date(2000, Calendar.JANUARY,1),
                2,
                3
        );
        shift2.setShiftId(SHIFT_KEY_INITIAL_2);
        shifts.add(shift2);
        lenient().when(shiftDao.findShiftByShiftId(anyInt())).thenAnswer((invocation) -> {
            if (invocation.getArgument(0).equals(shift1.getShiftId())) {
                return shift1;
            } else {
                return null;
            }
        });
        lenient().when(shiftDao.findAll()).thenAnswer((invocation) -> shifts);
        lenient().when(shiftDao.save(any(Shift.class))).thenAnswer(returnParameterAsAnswer);
    }

    /**
     * Test creating an employee.
     */
    @Test
    public void testCreateEmployee() {
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
     * Test creating an employee with a negative hours worked.
     */
    @Test
    public void testCreateEmployeeNegativeHoursWorked() {
        String error = null;
        Employee employee = null;
        try {
            employee = employeeService.createEmployee(
                    "boi",
                    "boi",
                    EMPLOYEE_KEY,
                    1234567891,
                    " 1",
                    -1
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(employee);
        // check error
        assertEquals("The hours worked must not be negative!", error);
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
                    EMPLOYEE_INITIAL_KEY_1,
                    newPhoneNumber,
                    newPassword,
                    1);
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
                        EMPLOYEE_INITIAL_KEY_1,
                        newPhoneNumber,
                        newPassword,
                        1);
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
                        EMPLOYEE_INITIAL_KEY_1,
                        newPhoneNumber,
                        newPassword,
                        1);
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
                        EMPLOYEE_INITIAL_KEY_1,
                        newPhoneNumber,
                        newPassword,
                        1);
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
    public void testUpdateEmployeeEmptyEmail() {
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
                    "",
                    newPhoneNumber,
                    newPassword,
                    1);
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
                    "boimailcom",
                    newPhoneNumber,
                    newPassword,
                    1);
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
            employee = employeeService.updateEmployee(
                    newFirstName,
                    newLastName,
                    EMPLOYEE_INITIAL_KEY_1,
                    newPhoneNumber,
                    newPassword,
                    1);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(employee);
        assertEquals("The password cannot be empty!", error);
    }

    /**
     * Test updating an employee with a negative hours worked.
     */
    @Test
    public void testUpdateEmployeeNegativeHoursWorked() {
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
                    EMPLOYEE_INITIAL_KEY_1,
                    newPhoneNumber,
                    newPassword,
                    -1);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(employee);
        assertEquals("The hours worked must not be negative!", error);
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
                        NONEXISTING_KEY,
                        newPhoneNumber,
                        newPassword,
                        1);
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
        } catch (Mar1HotelSystemException e) {
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
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check if the employee was deleted
        assertFalse(deleted);
        assertEquals("The employee does not exist!", error);
    }

    /**
     * Test retrieving all shifts
     */
    @Test
    public void testGetAllShifts() {
        ArrayList<Shift> shifts = null;
        try {
            shifts = (ArrayList<Shift>) employeeService.getAllShifts();
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        // Check not null
        assertNotNull(shifts);
        assertEquals(2, shifts.size());
        assertEquals(SHIFT_KEY_INITIAL_1, shifts.get(0).getShiftId());
        assertEquals(SHIFT_KEY_INITIAL_2, shifts.get(1).getShiftId());
    }

    /**
     * Test retrieving all shifts for an employee
     */
    @Test
    public void testGetShiftsEmployee() {
        ArrayList<Shift> shifts = null;
        try {
            shifts = (ArrayList<Shift>) employeeService.getShiftsEmployee(EMPLOYEE_INITIAL_KEY_1);
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        // Check not null
        assertNotNull(shifts);
        assertEquals(1, shifts.size());
        assertEquals(EMPLOYEE_INITIAL_KEY_1, shifts.get(0).getEmployee().getEmail());
    }

    /**
     * Test retrieving all shifts for an employee that doesn't exist
     */
    @Test
    public void testGetShiftsEmployeeNull() {
        ArrayList<Shift> shifts = null;
        String error = null;
        try {
            shifts = (ArrayList<Shift>) employeeService.getShiftsEmployee(NONEXISTING_KEY);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check not null
        assertNull(shifts);
        assertEquals("The employee does not exist!", error);
    }

    /**
     * Test retrieving a shift
     */
    @Test
    public void testGetShift() {
        Shift shift = null;
        try {
            shift = employeeService.getShift(SHIFT_KEY_INITIAL_1);
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        // Check not null
        assertNotNull(shift);
        assertEquals(SHIFT_KEY_INITIAL_1, shift.getShiftId());
    }

    /**
     * Test retrieving a shift that doesn't exist
     */
    @Test
    public void testGetNonExistingShift() {
        Shift shift = null;
        try {
            shift = employeeService.getShift(NONEXISTING_SHIFT_KEY);
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        // Check null
        assertNull(shift);
    }

    /**
     * Test creating shift
     */
    @Test
    public void testCreateShift() {
        Shift shift = null;
        try {
            shift = employeeService.createShift(
                    new Date(2000, Calendar.JANUARY,1),
                    1,
                    2,
                    EMPLOYEE_INITIAL_KEY_1
            );
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        // Check not null
        assertNotNull(shift);
        assertEquals(EMPLOYEE_INITIAL_KEY_1, shift.getEmployee().getEmail());
        assertEquals(1, shift.getStartTime());
        assertEquals(2, shift.getEndTime());
    }

    /**
     * Test creating shift with negative start time
     */
    @Test
    public void testCreateShiftNegativeStartTime() {
        Shift shift = null;
        String error = null;
        try {
            shift = employeeService.createShift(
                    new Date(2000, Calendar.JANUARY,1),
                    -1,
                    2,
                    EMPLOYEE_INITIAL_KEY_1
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(shift);
        assertEquals("The start time must be between 0 and 24!", error);
    }

    /**
     * Test creating shift with start time over 24
     */
    @Test
    public void testCreateTestStartTimeOver24() {
        Shift shift = null;
        String error = null;
        try {
            shift = employeeService.createShift(
                    new Date(2000, Calendar.JANUARY,1),
                    25,
                    2,
                    EMPLOYEE_INITIAL_KEY_1
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(shift);
        assertEquals("The start time must be between 0 and 24!", error);
    }

    /**
     * Test creating shift with negative end time
     */
    @Test
    public void testCreateShiftNegativeEndTime() {
        Shift shift = null;
        String error = null;
        try {
            shift = employeeService.createShift(
                    new Date(2000, Calendar.JANUARY,1),
                    1,
                    -1,
                    EMPLOYEE_INITIAL_KEY_1
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(shift);
        assertEquals("The end time must be between 0 and 24!", error);
    }

    /**
     * Test creating shift with end time over 24
     */
    @Test
    public void testCreatingShiftEndTimeOver24() {
        Shift shift = null;
        String error = null;
        try {
            shift = employeeService.createShift(
                    new Date(2000, Calendar.JANUARY,1),
                    1,
                    25,
                    EMPLOYEE_INITIAL_KEY_1
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(shift);
        assertEquals("The end time must be between 0 and 24!", error);
    }

    /**
     * Test creating shift with end time before start time
     */
    @Test
    public void testCreateShiftEndTimeBeforeStartTime() {
        Shift shift = null;
        String error = null;
        try {
            shift = employeeService.createShift(
                    new Date(2000, Calendar.JANUARY,1),
                    2,
                    1,
                    EMPLOYEE_INITIAL_KEY_1
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(shift);
        assertEquals("The end time must be after the start time!", error);
    }

    /**
     * Test creating shift with an employee that doesn't exist
     */
    @Test
    public void testCreatingShiftEmployeeNull() {
        Shift shift = null;
        String error = null;
        try {
            shift = employeeService.createShift(
                    new Date(2000, Calendar.JANUARY,1),
                    1,
                    2,
                    NONEXISTING_KEY
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(shift);
        assertEquals("The employee does not exist!", error);
    }

    /**
     * Test updating shift
     */
    @Test
    public void testUpdateShift() {
        Shift shift = null;
        try {
            shift = employeeService.updateShift(
                    SHIFT_KEY_INITIAL_1,
                    new Date(2000, Calendar.JANUARY,1),
                    4,
                    5
            );
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        // Check not null
        assertNotNull(shift);
        assertEquals(4, shift.getStartTime());
        assertEquals(5, shift.getEndTime());
        assertEquals(SHIFT_KEY_INITIAL_1, shift.getShiftId());
        assertEquals(EMPLOYEE_INITIAL_KEY_1, shift.getEmployee().getEmail());
        assertEquals(new Date(2000, Calendar.JANUARY,1), shift.getDate());
    }

    /**
     * Test updating shift with negative start time
     */
    @Test
    public void testUpdateShiftNegativeStartTime() {
        Shift shift = null;
        String error = null;
        try {
            shift = employeeService.updateShift(
                    SHIFT_KEY_INITIAL_1,
                    new Date(2000, Calendar.JANUARY,1),
                    -1,
                    5
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(shift);
        assertEquals("The start time must be between 0 and 24!", error);
    }

    /**
     * Test updating shift with start time over 24
     */
    @Test
    public void testUpdateShiftStartTimeOver24() {
        Shift shift = null;
        String error = null;
        try {
            shift = employeeService.updateShift(
                    SHIFT_KEY_INITIAL_1,
                    new Date(2000, Calendar.JANUARY,1),
                    25,
                    5
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(shift);
        assertEquals("The start time must be between 0 and 24!", error);
    }

    /**
     * Test updating shift with negative end time
     */
    @Test
    public void testUpdateShiftNegativeEndTime() {
        Shift shift = null;
        String error = null;
        try {
            shift = employeeService.updateShift(
                    SHIFT_KEY_INITIAL_1,
                    new Date(2000, Calendar.JANUARY,1),
                    1,
                    -1
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(shift);
        assertEquals("The end time must be between 0 and 24!", error);
    }

    /**
     * Test updating shift with end time over 24
     */
    @Test
    public void testUpdateShiftEndTimeOver24() {
        Shift shift = null;
        String error = null;
        try {
            shift = employeeService.updateShift(
                    SHIFT_KEY_INITIAL_1,
                    new Date(2000, Calendar.JANUARY,1),
                    1,
                    25
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(shift);
        assertEquals("The end time must be between 0 and 24!", error);
    }

    /**
     * Test updating shift with end time before start time
     */
    @Test
    public void testUpdateShiftEndTimeBeforeStartTime() {
        Shift shift = null;
        String error = null;
        try {
            shift = employeeService.updateShift(
                    SHIFT_KEY_INITIAL_1,
                    new Date(2000, Calendar.JANUARY,1),
                    2,
                    1
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(shift);
        assertEquals("The end time must be after the start time!", error);
    }

    /**
     * Test updating shift that doesn't exist
     */
    @Test
    public void testUpdateShiftNull() {
        Shift shift = null;
        String error = null;
        try {
            shift = employeeService.updateShift(
                    NONEXISTING_SHIFT_KEY,
                    new Date(2000, Calendar.JANUARY,1),
                    1,
                    2
            );
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check null
        assertNull(shift);
        assertEquals("The shift does not exist!", error);
    }

    /**
     * Test deleting shift
     */
    @Test
    public void testDeleteShift() {
        boolean deleted = false;
        try {
            deleted = employeeService.deleteShift(SHIFT_KEY_INITIAL_1);
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        // Check if the shift was deleted
        assertTrue(deleted);
        verify(shiftDao, times(1)).delete(employeeService.getShift(SHIFT_KEY_INITIAL_1));
    }

    /**
     * Test deleting shift that isn't registered in the database
     */
    @Test
    public void testDeleteShiftInvalidId() {
        boolean deleted = false;
        String error = null;
        try {
            deleted = employeeService.deleteShift(NONEXISTING_SHIFT_KEY);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
        }
        // Check if the shift was deleted
        assertFalse(deleted);
        assertEquals("The shift does not exist!", error);
    }

}
