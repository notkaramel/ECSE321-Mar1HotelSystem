package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;

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
 * Service tests for the Customer class.
 *
 * @author Liu, ZiXu
 */
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
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
    private CustomerService customerService;
    private static final String CUSTOMER_KEY = "TestEmail@gmail.com";
    private static final String NONEXISTING_KEY = "NotACustomer@mail.com";
    private static final String CUSTOMER_INITIAL_KEY_1 = "test1@mail.com";
    private static final String CUSTOMER_INITIAL_KEY_2 = "test2@mail.com";

    /**
     * Set mock customer output before each test.
     */
    @BeforeEach
    public void setMockOutput() {
        lenient().when(customerDao.findCustomerByEmail(anyString())).thenAnswer((invocation) -> {
            if (invocation.getArgument(0).equals("test1@mail.com")) {
                return new Customer(
                        "Josh",
                        "Deb",
                        CUSTOMER_INITIAL_KEY_1,
                        1234567890,
                        "TestPassword"

                );
            } else {
                return null;
            }
        });
        lenient().when(customerDao.findAll()).thenAnswer((invocation) -> {
            ArrayList<Customer> customers = new ArrayList<>();
            Customer customer1 = new Customer(
                    "Josh",
                    "Deb",
                    CUSTOMER_INITIAL_KEY_1,
                    1234567890,
                    "TestPassword"
            );
            customers.add(customer1);
            Customer customer2 = new Customer(
                    "Seth",
                    "Royal",
                    CUSTOMER_INITIAL_KEY_2,
                    1231232312,
                    "TestPassword2"
            );
            customers.add(customer2);
            return customers;
        });
        Answer<?> returnParameterAsAnswer =
                (InvocationOnMock invocation) -> {
                    return invocation.getArgument(0);
                };
        lenient().when(customerDao.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
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
     * Test creating a customer with an existing email
     */
    @Test
    public void testCreateCustomerExistingEmail() {;
        String error = null;
        Customer customer = null;
        try {
            customer = customerService.createCustomer(
                    "Josh",
                    "Deb",
                    CUSTOMER_INITIAL_KEY_1,
                    1234567890,
                    "TestPassword"
            );
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check null
        assertNull(customer);
        // check error
        assertEquals("User using that email already exists!", error);
    }

    /**
     * Test retrieving a customer.
     */
    @Test
    public void testGetExistingCustomer() {
        assertEquals(CUSTOMER_INITIAL_KEY_1, customerService.getCustomer(CUSTOMER_INITIAL_KEY_1).getEmail());
    }

    /**
     * Test retrieving a non-existing customer.
     */
    @Test
    public void testGetNonExistingCustomer() {
        assertNull(customerService.getCustomer(NONEXISTING_KEY));
    }

    /**
     * Test retrieving all customers.
     */
    @Test
    public void testGetAllCustomers() {
        ArrayList<Customer> listOfCustomers = (ArrayList<Customer>) customerService.getAllCustomers();
        assertEquals(2, listOfCustomers.size());
        for(Customer customer : listOfCustomers) {
            assertNotNull(customer);
        }
    }

    /**
     * Test updating a customer.
     */
    @Test
    public void testUpdateCustomer() {
        String newFirstName = "Josh";
        String newLastName = "Deb";
        String newPassword = "TestPassword";
        long newPhoneNumber = 1234567890;
        Customer customer = null;
        try {
            customer = customerService.updateCustomer(
                    newFirstName,
                    newLastName,
                    newPhoneNumber,
                    newPassword,
                    CUSTOMER_INITIAL_KEY_1);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        assertNotNull(customer);
        assertEquals(CUSTOMER_INITIAL_KEY_1, customer.getEmail());
    }

    /**
     * Test updating a customer with empty first name.
     */
    @Test
    public void testUpdateCustomerInvalidFirstName() {
        String newFirstName = "";
        String newLastName = "Deb";
        String newPassword = "TestPassword";
        long newPhoneNumber = 1234567890;
        Customer customer = null;
        String error = null;
        try {
            customer = customerService.updateCustomer(
                    newFirstName,
                    newLastName,
                    newPhoneNumber,
                    newPassword,
                    CUSTOMER_INITIAL_KEY_1);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer);
        assertEquals("The first name cannot be empty!", error);
    }

    /**
     * Test updating a customer with empty last name.
     */
    @Test
    public void testUpdateCustomerInvalidLastName() {
        String newFirstName = "Josh";
        String newLastName = "";
        String newPassword = "TestPassword";
        long newPhoneNumber = 1234567890;
        Customer customer = null;
        String error = null;
        try {
            customer = customerService.updateCustomer(
                    newFirstName,
                    newLastName,
                    newPhoneNumber,
                    newPassword,
                    CUSTOMER_INITIAL_KEY_1);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer);
        assertEquals("The last name cannot be empty!", error);
    }

    /**
     * Test updating a customer with empty password.
     */
    @Test
    public void testUpdateCustomerInvalidPassword() {
        String newFirstName = "Josh";
        String newLastName = "Deb";
        String newPassword = "";
        long newPhoneNumber = 1234567890;
        Customer customer = null;
        String error = null;
        try {
            customer = customerService.updateCustomer(
                    newFirstName,
                    newLastName,
                    newPhoneNumber,
                    newPassword,
                    CUSTOMER_INITIAL_KEY_1);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer);
        assertEquals("The password cannot be empty!", error);
    }

    /**
     * Test updating a customer that isn't in the database.
     */
    @Test
    public void testUpdateCustomerEmailNotFound() {
        String newFirstName = "Josh";
        String newLastName = "Deb";
        String newPassword = "TestPassword";
        long newPhoneNumber = 1234567890;
        Customer customer = null;
        String error = null;
        try {
            customer = customerService.updateCustomer(
                    newFirstName,
                    newLastName,
                    newPhoneNumber,
                    newPassword,
                    NONEXISTING_KEY);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer);
        assertEquals("The customer does not exist!", error);
    }

    /**
     * Test deleting a customer.
     */
    @Test
    public void testDeleteCustomer() {
        boolean deleted = false;
        try {
            deleted = customerService.deleteCustomer(CUSTOMER_INITIAL_KEY_1);
        } catch (Exception e) {
            fail();
        }
        // Check if the customer was deleted
        assertTrue(deleted);
        verify(customerDao, times(1)).delete(customerService.getCustomer(CUSTOMER_INITIAL_KEY_1));
    }
}
