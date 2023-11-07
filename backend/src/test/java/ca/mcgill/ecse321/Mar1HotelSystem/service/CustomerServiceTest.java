package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.CustomerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;

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
 * Service tests for the Customer class.
 *
 * @author Liu, ZiXu
 */
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerDao;

    @InjectMocks
    private CustomerService customerService;

    private static final String CUSTOMER_KEY = "TestEmail";
    private static final String NONEXISTING_KEY = "NotACustomer";

    /**
     * Set mock customer output before each test.
     */
    @BeforeEach
    public void setMockOutput() {
        lenient().when(customerDao.findCustomerByEmail(anyString())).thenAnswer((invocation) -> {
            if (invocation.getArgument(0).equals(CUSTOMER_KEY)) {
                return new Customer(
                        "Josh",
                        "Deb",
                        CUSTOMER_KEY,
                        1234567890,
                        "TestPassword"

                );
            } else {
                return null;
            }
        });
        lenient().when(customerDao.findAll()).thenAnswer((invocation) -> {
            ArrayList<Customer> customers = new ArrayList<>();
            Customer customer = new Customer(
                    "Josh",
                    "Deb",
                    CUSTOMER_KEY,
                    1234567890,
                    "TestPassword"
            );
            customers.add(customer);
            return customers;
        });
    }

    /**
     * Test creating a customer.
     */
    @Test
    public void testCreateCustomer() {
        //assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        try {
            customer = customerService.createCustomer(
                    "Josh",
                    "Deb",
                    CUSTOMER_KEY,
                    1234567890,
                    "TestPassword"
            );
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        assertNotNull(customer);
        assertEquals(CUSTOMER_KEY, customer.getEmail());
    }

    /**
     * Test creating a customer with empty strings.
     */
    @Test
    public void testCreateCustomerEmpty() {
        //assertEquals(0, customerService.getAllCustomers().size());
        String error = null;
        Customer customer = null;
        try {
            customer = customerService.createCustomer(
                    "",
                    "",
                    "",
                    0,
                    ""
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(customer);
        // check error
        assertEquals("The first name cannot be empty!", error);
    }

    /**
     * Test creating a customer with nulls.
     */
    @Test
    public void testCreateCustomerNull() {
        //assertEquals(0, customerService.getAllCustomers().size());
        String error = null;
        Customer customer = null;
        try {
            customer = customerService.createCustomer(
                    null,
                    null,
                    null,
                    0,
                    null
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(customer);
        // check error
        assertEquals("The first name cannot be empty!", error);
    }

    /**
     * Test creating a customer with a first name that is an empty string.
     */
    @Test
    public void testCreateCustomerFirstNameSpaces() {
        //assertEquals(0, customerService.getAllCustomers().size());
        String error = null;
        Customer customer = null;
        try {
            customer = customerService.createCustomer(
                    " ",
                    "Deb",
                    CUSTOMER_KEY,
                    1234567890,
                    "TestPassword"
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(customer);
        // check error
        assertEquals("The first name cannot be empty!", error);
    }

    /**
     * Test creating a customer with a last name that is an empty string.
     */
    @Test
    public void testCreateCustomerLastNameSpaces() {
        //assertEquals(0, customerService.getAllCustomers().size());
        String error = null;
        Customer customer = null;
        try {
            customer = customerService.createCustomer(
                    "Josh",
                    " ",
                    CUSTOMER_KEY,
                    1234567890,
                    "TestPassword"
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(customer);
        // check error
        assertEquals("The last name cannot be empty!", error);
    }

    /**
     * Test creating a customer with an email that is an empty string.
     */
    @Test
    public void testCreateCustomerEmailSpaces() {
        //assertEquals(0, customerService.getAllCustomers().size());
        String error = null;
        Customer customer = null;
        try {
            customer = customerService.createCustomer(
                    "Josh",
                    "Deb",
                    " ",
                    1234567890,
                    "TestPassword"
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(customer);
        // check error
        assertEquals("The email cannot be empty!", error);
    }

    /**
     * Test creating a customer with an email without "@".
     */
    @Test
    public void testCreateCustomerEmailNoA() {
        //assertEquals(0, customerService.getAllCustomers().size());
        String error = null;
        Customer customer = null;
        try {
            customer = customerService.createCustomer(
                    "Josh",
                    "Deb",
                    "joshdebmcgill.ca",
                    1234567890,
                    "TestPassword"
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(customer);
        // check error
        assertEquals("The email is invalid!", error);
    }

    /**
     * Test creating a customer with an email without ".".
     */
    @Test
    public void testCreateCustomerEmailNoPeriod() {
        //assertEquals(0, customerService.getAllCustomers().size());
        String error = null;
        Customer customer = null;
        try {
            customer = customerService.createCustomer(
                    "Josh",
                    "Deb",
                    "joshdeb@mcgillca",
                    1234567890,
                    "TestPassword"
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(customer);
        // check error
        assertEquals("The email is invalid!", error);
    }

    /**
     * Test creating a customer with a password that is an empty string.
     */
    @Test
    public void testCreateCustomerPasswordSpaces() {
        //assertEquals(0, customerService.getAllCustomers().size());
        String error = null;
        Customer customer = null;
        try {
            customer = customerService.createCustomer(
                    "Josh",
                    "Deb",
                    CUSTOMER_KEY,
                    1234567890,
                    " "
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(customer);
        // check error
        assertEquals("The password cannot be empty!", error);
    }

    /**
     * Test retrieving a customer.
     */
    @Test
    public void testGetExistingGeneralUser() {
        assertEquals(CUSTOMER_KEY, customerService.getCustomer(CUSTOMER_KEY).getEmail());
    }

    /**
     * Test retrieving a non-existing customer.
     */
    @Test
    public void testGetNonExistingPerson() {
        assertNull(customerService.getCustomer(NONEXISTING_KEY));
    }
}