package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.ShiftRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.EmployeeDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.ShiftDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Shift;
import ca.mcgill.ecse321.Mar1HotelSystem.service.CustomerService;
import ca.mcgill.ecse321.Mar1HotelSystem.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the Employee controller
 *
 * @author ZiXu Liu (@ARandomPi)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ShiftRepository shiftDao;

    private final String EMPLOYEE_KEY = "fisi@mail.com";
    private final String CUSTOMER_KEY = "still11testsleft@mail.com";


    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        ArrayList<Customer> customers = new ArrayList<>(customerService.getAllCustomers());
        for (Customer customer : customers) {
            customerService.deleteCustomer(customer.getEmail());
        }
        ArrayList<Shift> shifts = new ArrayList<>(employeeService.getAllShifts());
        for (Shift shift : shifts) {
            employeeService.deleteShift(shift.getShiftId());
        }
        ArrayList<Employee> employees = new ArrayList<>(employeeService.getAllEmployees());
        for (Employee employee : employees) {
            employeeService.deleteEmployee(employee.getEmail());
        }
    }

    /**
     * Helper method to create a lot a customer, employee and shift
     */
    public void createEmployeesAndCustomers() {
        customerService.createCustomer(
                "Send",
                "Help",
                CUSTOMER_KEY,
                123213123,
                "pls");
        Employee employee = employeeService.createEmployee(
                "I",
                "Need",
                EMPLOYEE_KEY,
                12345555,
                "sleep",
                0);
        // Manual creation of a shift to set a shift id
        Shift shift = new Shift(
                employee,
                new Date(2000,2,20),
                1,
                3);
        shiftDao.save(shift);
    }

    /**
     * Test getting all employees
     */
    @Test
    public void testGetAllEmployees() {
        createEmployeesAndCustomers();
        ResponseEntity<EmployeeDto[]> responseEntity = restTemplate.getForEntity(
                "/employees",
                EmployeeDto[].class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        EmployeeDto[] employeeDtos = responseEntity.getBody();
        assertNotNull(employeeDtos);
        assertEquals(1, employeeDtos.length);
        assertEquals(EMPLOYEE_KEY, employeeDtos[0].getEmail());
    }

    /**
     * Test getting an employee by email
     */
    @Test
    public void testGetEmployeeByEmail() {
        createEmployeesAndCustomers();
        ResponseEntity<EmployeeDto> responseEntity = restTemplate.getForEntity(
                "/employee/fisi@mail.com",
                EmployeeDto.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        EmployeeDto employeeDto = responseEntity.getBody();
        assertNotNull(employeeDto);
        assertEquals(EMPLOYEE_KEY, employeeDto.getEmail());
    }

    /**
     * Test getting an employee with an invalid email
     */
    @Test
    public void testGetEmployeeInvalidEmail() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/employee/boi@mail.com",
                String.class);
        assertNotNull(responseEntity);
        assertEquals("The employee does not exist!", responseEntity.getBody());
    }

    /**
     * Test getting all shifts
     */
    @Test
    public void testGetAllShifts() {
        createEmployeesAndCustomers();
        ResponseEntity<ShiftDto[]> responseEntity = restTemplate.getForEntity(
                "/employee/shifts",
                ShiftDto[].class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ShiftDto[] shifts = responseEntity.getBody();
        assertNotNull(shifts);
        assertEquals(1, shifts.length);
        assertEquals(0, shifts[0].getShiftId());
    }

    /**
     * Test getting all shifts by employee email
     */
    @Test
    public void testGetAllShiftsByEmail() {
        createEmployeesAndCustomers();
        String url = "/employee/" + EMPLOYEE_KEY + "/shifts";
        ResponseEntity<ShiftDto[]> responseEntity = restTemplate.getForEntity(
                url,
                ShiftDto[].class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ShiftDto[] shifts = responseEntity.getBody();
        assertNotNull(shifts);
        assertEquals(1, shifts.length);
        assertEquals(0, shifts[0].getShiftId());
    }

    /**
     * Test getting all shifts by an invalid employee email
     */
    @Test
    public void testGetAllShiftsByInvalidEmail() {
        createEmployeesAndCustomers();
        String url = "/employee/lhjnasdljasd@mail.com/shifts";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                url,
                String.class);
        assertNotNull(responseEntity);
        assertEquals("The employee does not exist!", responseEntity.getBody());
    }

    /**
     * Test getting a shift by shift id
     */
    @Test
    public void testGetShift() {
        createEmployeesAndCustomers();
        /*
        This id part needs a little explanation:

        The shift id is generated by the database, so it is not always 0.
        Even if I try to set it, for example in the @BeforeEach and @AfterEach methods, the fact that
        I need to save the new id with the DAO means that it will be changed again. I didn't have this
        issue in the service tests because of Mock, which always returns the id I set (unlike a real database).

        So, I just get the first shift in the list and use its id (since I know there is only 1 shift in the list).
        and add that id to the url.
         */
        int id = employeeService.getAllShifts().get(0).getShiftId();
        String url = "/employee/shift/" + id;
        ResponseEntity<ShiftDto> responseEntity = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                ShiftDto.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ShiftDto shift = responseEntity.getBody();
        assertNotNull(shift);
        assertEquals(0, shift.getShiftId());
    }

    /**
     * Test getting a shift with an invalid shift id
     */
    @Test
    public void testGetShiftInvalidId() {
        createEmployeesAndCustomers();
        String url = "/employee/shift/10";
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                String.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("The shift does not exist!", responseEntity.getBody());
    }

    /**
     * Test creating an employee
     */
    @Test
    public void testCreateEmployee() {
        createEmployeesAndCustomers();
        EmployeeDto employeeDto = new EmployeeDto(
                "A",
                "B",
                "boi@mail.com",
                123123123,
                "pls",
                0);
        ResponseEntity<EmployeeDto> responseEntity = restTemplate.postForEntity(
                "/employee",
                employeeDto,
                EmployeeDto.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        EmployeeDto employee = responseEntity.getBody();
        assertNotNull(employee);
        assertEquals("A", employee.getFirstName());
        assertEquals("B", employee.getLastName());
        assertEquals("boi@mail.com", employee.getEmail());
        assertEquals(123123123, employee.getPhoneNumber());
        assertEquals("pls", employee.getPassword());
        assertEquals(0, employee.getHoursWorked());
    }

    /**
     * Test creating an employee with an email that is taken
     */
    @Test
    public void testCreateEmployeeEmailTaken() {
        createEmployeesAndCustomers();
        EmployeeDto employeeDto = new EmployeeDto(
                "A",
                "B",
                CUSTOMER_KEY,
                123123123,
                "pls",
                0);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "/employee",
                employeeDto,
                String.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("User using that email already exists!", responseEntity.getBody());
    }

    /**
     * Method to convert an employee into a DTO
     */
    private EmployeeDto convertToDto(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("There is no such employee!");
        }
        return new EmployeeDto(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getPassword(),
                employee.getHoursWorked());
    }

    /**
     * Test creating a shift
     */
    @Test
    public void testCreateShift() {
        createEmployeesAndCustomers();
        ShiftDto shiftDto = new ShiftDto(
                convertToDto(employeeService.getEmployee(EMPLOYEE_KEY)),
                new Date(2000, Calendar.JANUARY,20),
                1,
                3);
        ResponseEntity<ShiftDto> responseEntity = restTemplate.postForEntity(
                "/employee/" + EMPLOYEE_KEY + "/shift",
                shiftDto,
                ShiftDto.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        ShiftDto shift = responseEntity.getBody();
        assertNotNull(shift);
        assertEquals(0, shift.getShiftId());
        assertEquals(1, shift.getStartTime());
        assertEquals(3, shift.getEndTime());
    }

    /**
     * Test creating a shift with an invalid employee email
     */
    @Test
    public void testCreateShiftInvalidEmail() {
        createEmployeesAndCustomers();
        ShiftDto shiftDto = new ShiftDto(
                null,
                new Date(2000, Calendar.JANUARY, 20),
                1,
                3);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "/employee/asdsda@mail.com/shift",
                shiftDto,
                String.class);
        assertNotNull(responseEntity);
        assertEquals("The employee does not exist!", responseEntity.getBody());
    }

    /**
     * Test updating an employee
     */
    @Test
    public void testUpdateEmployee() {
        createEmployeesAndCustomers();
        EmployeeDto employeeDto = new EmployeeDto(
                "A",
                "B",
                EMPLOYEE_KEY,
                123123123,
                "pls",
                0);
        ResponseEntity<EmployeeDto> responseEntity = restTemplate.exchange(
                "/employee/" + EMPLOYEE_KEY,
                org.springframework.http.HttpMethod.PUT,
                new HttpEntity<>(employeeDto),
                EmployeeDto.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        EmployeeDto employee = responseEntity.getBody();
        assertNotNull(employee);
        assertEquals("A", employee.getFirstName());
        assertEquals("B", employee.getLastName());
        assertEquals(EMPLOYEE_KEY, employee.getEmail());
        assertEquals(123123123, employee.getPhoneNumber());
        assertEquals("pls", employee.getPassword());
        assertEquals(0, employee.getHoursWorked());
    }

    /**
     * Test updating an employee with an invalid email
     */
    @Test
    public void testUpdateEmployeeInvalidEmail() {
        EmployeeDto employeeDto = new EmployeeDto(
                "A",
                "B",
                "wwwww@mail.com",
                123123123,
                "pls",
                0);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "/employee/" + "wwwww@mail.com",
                org.springframework.http.HttpMethod.PUT,
                new HttpEntity<>(employeeDto),
                String.class);
        assertNotNull(responseEntity);
        assertEquals("The employee does not exist!", responseEntity.getBody());
    }

    /**
     * Test updating a shift
     */
    @Test
    public void testUpdateShift() {
        createEmployeesAndCustomers();
        ShiftDto shiftDto = new ShiftDto(
                convertToDto(employeeService.getEmployee(EMPLOYEE_KEY)),
                new Date(2000,Calendar.FEBRUARY,20),
                1,
                3);
        /*
        This id part needs a little explanation:

        The shift id is generated by the database, so it is not always 0.
        Even if I try to set it, for example in the @BeforeEach and @AfterEach methods, the fact that
        I need to save the new id with the DAO means that it will be changed again. I didn't have this
        issue in the service tests because of Mock, which always returns the id I set (unlike a real database).

        So, I just get the first shift in the list and use its id (since I know there is only 1 shift in the list).
        and add that id to the url.
         */
        int url = employeeService.getAllShifts().get(0).getShiftId();
        ResponseEntity<ShiftDto> responseEntity = restTemplate.exchange(
                "/employee/shift/" + url,
                org.springframework.http.HttpMethod.PUT,
                new HttpEntity<>(shiftDto),
                ShiftDto.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ShiftDto shift = responseEntity.getBody();
        assertNotNull(shift);
        assertEquals(0, shift.getShiftId());
        assertEquals(1, shift.getStartTime());
        assertEquals(3, shift.getEndTime());
    }

    /**
     * Test updating a shift with an invalid shift id
     */
    @Test
    public void testUpdateShiftInvalidId() {
        createEmployeesAndCustomers();
        ShiftDto shiftDto = new ShiftDto(
                convertToDto(employeeService.getEmployee(EMPLOYEE_KEY)),
                new Date(2000,Calendar.FEBRUARY,20),
                1,
                3);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "/employee/shift/10",
                org.springframework.http.HttpMethod.PUT,
                new HttpEntity<>(shiftDto),
                String.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("The shift does not exist!", responseEntity.getBody());
    }

    /**
     * Test deleting an employee and its shifts
     */
    @Test
    public void testDeleteEmployeeAndShift() {
        createEmployeesAndCustomers();
        /*
        This id part needs a little explanation:

        The shift id is generated by the database, so it is not always 0.
        Even if I try to set it, for example in the @BeforeEach and @AfterEach methods, the fact that
        I need to save the new id with the DAO means that it will be changed again. I didn't have this
        issue in the service tests because of Mock, which always returns the id I set (unlike a real database).

        So, I just get the first shift in the list and use its id (since I know there is only 1 shift in the list).
        and add that id to the url.
         */
        int url = employeeService.getAllShifts().get(0).getShiftId();
        ResponseEntity<String> responseEntityShift = restTemplate.exchange(
                "/employee/shift/" + url,
                org.springframework.http.HttpMethod.DELETE,
                null,
                String.class);
        assertNotNull(responseEntityShift);
        assertEquals(HttpStatus.OK, responseEntityShift.getStatusCode());
        assertNull(employeeService.getShift(0));
        ResponseEntity<String> responseEntityEmployee = restTemplate.exchange(
                "/employee/" + EMPLOYEE_KEY,
                org.springframework.http.HttpMethod.DELETE,
                null,
                String.class);
        assertNotNull(responseEntityEmployee);
        assertEquals(HttpStatus.OK, responseEntityEmployee.getStatusCode());
        assertNull(employeeService.getEmployee(EMPLOYEE_KEY));
    }

    /**
     * Test deleting an employee with an invalid email
     */
    @Test
    public void testDeleteEmployeeInvalidEmail() {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "/employee/" + EMPLOYEE_KEY,
                org.springframework.http.HttpMethod.DELETE,
                null,
                String.class);
        assertNotNull(responseEntity);
        assertEquals("The employee does not exist!", responseEntity.getBody());
    }

    /**
     * Test deleting a shift with an invalid shift id
     */
    @Test
    public void testDeleteShiftInvalidId() {
        createEmployeesAndCustomers();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "/employee/shift/10",
                org.springframework.http.HttpMethod.DELETE,
                null,
                String.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("The shift does not exist!", responseEntity.getBody());
    }
}
