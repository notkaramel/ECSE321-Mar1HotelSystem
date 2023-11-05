package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

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

        @Test void testCreateCustomerEmailTaken() {
            employeeService.createEmployee(
                    "git",
                    "gud",
                    "boiNotRandom2@mail.com",
                    5555552,
                    "password12342",
                    0);
            CustomerDto customerDto = new CustomerDto(
                    "ARandom2",
                    "Pi2",
                    "boiNotRandom2@mail.com",
                    5555552,
                    "password12342");
            ResponseEntity<CustomerDto> responseEntity = restTemplate.postForEntity(
                    "/customer",
                    customerDto,
                    CustomerDto.class);
            assertNotNull(responseEntity);
            assertEquals(
                    HttpStatus.BAD_REQUEST,
                    responseEntity.getStatusCode(),
                    "The status code should be Bad Request!");
        }
}
