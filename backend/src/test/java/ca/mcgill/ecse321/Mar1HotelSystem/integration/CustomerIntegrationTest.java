package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import ca.mcgill.ecse321.Mar1HotelSystem.dto.CustomerDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import ca.mcgill.ecse321.Mar1HotelSystem.service.CustomerService;
import ca.mcgill.ecse321.Mar1HotelSystem.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the Customer controller
 *
 * @author ZiXu Liu (@ARandomPi)
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerIntegrationTest {
        @Autowired
        private TestRestTemplate restTemplate;
        @Autowired
        private CustomerService customerService;
        @Autowired
        private EmployeeService employeeService;

        @BeforeEach
        @AfterEach
        public void clearDatabase() {
            ArrayList<Customer> customers = new ArrayList<>(customerService.getAllCustomers());
            for (Customer customer : customers) {
                customerService.deleteCustomer(customer.getEmail());
            }
            ArrayList<Employee> employees = new ArrayList<>(employeeService.getAllEmployees());
            for (Employee employee : employees) {
                employeeService.deleteEmployee(employee.getEmail());
            }
        }

    /**
     * Test for getting all customers
     */
    @Test
    public void testGetAllCustomers() {
        customerService.createCustomer(
                "git",
                "gud",
                "a@mail.com",
                123123,
                "dddd");
        ResponseEntity<CustomerDto[]> responseEntity = restTemplate.getForEntity(
                "/customers",
                CustomerDto[].class);
        assertNotNull(responseEntity);
        assertEquals(
                HttpStatus.OK,
                responseEntity.getStatusCode(),
                "The status code should be OK!");
        assertEquals(1,
                Objects.requireNonNull(responseEntity.getBody()).length,
                "There should be 1 customer in the body!");
        CustomerDto customerDto = responseEntity.getBody()[0];
        assertNotNull(customerDto, "The customer should not be null!");
        assertEquals(
                "a@mail.com",
                customerDto.getEmail(),
                "The email should be the same between DTO and the domain object!");
    }

    /**
     * Test for getting a customer by email
     */
    @Test
    public void testGetCustomerByEmail() {
        customerService.createCustomer(
                "git",
                "gud",
                "a@mail.com",
                123123,
                "dddd");
        ResponseEntity<CustomerDto> responseEntity = restTemplate.getForEntity(
                "/customer/a@mail.com",
                CustomerDto.class);
        assertNotNull(responseEntity);
        assertEquals(
                HttpStatus.OK,
                responseEntity.getStatusCode(),
                "The status code should be OK!");
        CustomerDto customerDto = responseEntity.getBody();
        assertNotNull(customerDto, "The customer should not be null!");
        assertEquals(
                "a@mail.com",
                customerDto.getEmail(), "The email should be the same between DTO and the domain object!");
    }

    /**
     * Test for creating a customer
     */
    @Test
    public void testCreateCustomer() {
            CustomerDto customerDto = new CustomerDto(
                    "ARandom",
                    "Pi",
                    "boiNotRandom@mail.com",
                    555555,
                    "password1234");
            ResponseEntity<CustomerDto> responseEntity = restTemplate.postForEntity(
                    "/customer",
                    customerDto,
                    CustomerDto.class);
            assertNotNull(responseEntity);
            assertEquals(
                    HttpStatus.CREATED,
                    responseEntity.getStatusCode(),
                    "The status code should be Created!");
            CustomerDto responseCustomerDto = responseEntity.getBody();
            assertNotNull(responseCustomerDto, "The response body should not be null!");
            assertEquals(
                    customerDto.getFirstName(),
                    responseCustomerDto.getFirstName(),
                    "The first name should be the same between DTOs!");
            assertEquals(
                    customerDto.getLastName(),
                    responseCustomerDto.getLastName(),
                    "The last name should be the same between DTOs!");
            assertEquals(
                    customerDto.getEmail(),
                    responseCustomerDto.getEmail(),
                    "The email should be the same between DTOs!");
            assertEquals(
                    customerDto.getPhoneNumber(),
                    responseCustomerDto.getPhoneNumber(),
                    "The phone number should be the same between DTOs!");
            assertEquals(
                    customerDto.getPassword(),
                    responseCustomerDto.getPassword(),
                    "The password should be the same between DTOs!");
            Customer customer = customerService.getCustomer(customerDto.getEmail());
            assertNotNull(customer, "The customer should not be null!");
            assertEquals(
                    customerDto.getFirstName(),
                    customer.getFirstName(),
                    "The first name should be the same between DTO and the domain object!");
            assertEquals(
                    customerDto.getLastName(),
                    customer.getLastName(),
                    "The last name should be the same between DTO and the domain object!");
            assertEquals(
                    customerDto.getEmail(),
                    customer.getEmail(),
                    "The email should be the same between DTO and the domain object!");
            assertEquals(
                    customerDto.getPhoneNumber(),
                    customer.getPhoneNumber(),
                    "The phone number should be the same between DTO and the domain object!");
            assertEquals(
                    customerDto.getPassword(),
                    customer.getPassword(),
                    "The password should be the same between DTO and the domain object!");

        }

    /**
     * Test for creating a customer with an invalid email
     */
    @Test
    public void testCreateCustomerEmailTaken() {
            customerService.createCustomer(
                    "git",
                    "gud",
                    "boiNotRandom2@mail.com",
                    5555552,
                    "password12342");
            CustomerDto customerDto = new CustomerDto(
                    "ARandom2",
                    "Pi2",
                    "boiNotRandom2@mail.com",
                    5555552,
                    "password12342");
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                    "/customer",
                    customerDto,
                    String.class);
            assertNotNull(responseEntity);
            assertEquals(
                    "User using that email already exists!",
                    responseEntity.getBody());
        }

    /**
     * Test for updating a customer
     */
    @Test
    public void testUpdateCustomer() {
        customerService.createCustomer(
                "git",
                "gud",
                "abc@mail.com",
                123123,
                "dddd");
        CustomerDto customerDto = new CustomerDto(
                "ARandom",
                "Pi",
                "abc@mail.com",
                555555,
                "password1234");
        ResponseEntity<CustomerDto> responseEntity = restTemplate.exchange(
                "/customer/abc@mail.com",
                org.springframework.http.HttpMethod.PUT,
                new HttpEntity<>(customerDto),
                CustomerDto.class);
        assertNotNull(responseEntity);
        assertEquals(
                HttpStatus.OK,
                responseEntity.getStatusCode(),
                "The status code should be OK!");
        CustomerDto responseCustomerDto = responseEntity.getBody();
        assertNotNull(responseCustomerDto, "The response body should not be null!");
        assertEquals(
                customerDto.getFirstName(),
                responseCustomerDto.getFirstName(),
                "The first name should be the same between DTOs!");
        assertEquals(
                customerDto.getLastName(),
                responseCustomerDto.getLastName(),
                "The last name should be the same between DTOs!");
        assertEquals(
                customerDto.getEmail(),
                responseCustomerDto.getEmail(),
                "The email should be the same between DTOs!");
        assertEquals(
                customerDto.getPhoneNumber(),
                responseCustomerDto.getPhoneNumber(),
                "The phone number should be the same between DTOs!");
        assertEquals(
                customerDto.getPassword(),
                responseCustomerDto.getPassword(),
                "The password should be the same between DTOs!");
    }

    /**
     * Test for updating a customer with an invalid email
     */
    @Test
    public void testUpdateCustomerInvalidEmail() {
        CustomerDto customerDto = new CustomerDto(
                "ARandom",
                "Pi",
                "doot@mail.com",
                555555,
                "password1234");
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "/customer/doot@mail.com",
                org.springframework.http.HttpMethod.PUT,
                new HttpEntity<>(customerDto),
                String.class);
        assertNotNull(responseEntity);
        assertEquals(
                "The customer does not exist!",
                responseEntity.getBody());
    }

    /**
     * Test for deleting a customer
     */
    @Test
    public void testDeleteCustomer() {
        customerService.createCustomer(
                "git",
                "gud",
                "boi@mail.com",
                123123,
                "dddd");
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "/customer/boi@mail.com",
                org.springframework.http.HttpMethod.DELETE,
                null,
                String.class);
        assertNotNull(responseEntity);
        assertEquals(
                HttpStatus.OK,
                responseEntity.getStatusCode(),
                "The status code should be OK!");
        assertNull(customerService.getCustomer("boi@mail.com"));
    }

    /**
     * Test for deleting a customer with an invalid email
     */
    @Test
    public void testDeleteCustomerInvalidEmail() {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "/customer/abc@mail.com",
                org.springframework.http.HttpMethod.DELETE,
                null,
                String.class);
        assertNotNull(responseEntity);
        assertEquals(
                "The customer does not exist!",
                responseEntity.getBody());
    }
}
