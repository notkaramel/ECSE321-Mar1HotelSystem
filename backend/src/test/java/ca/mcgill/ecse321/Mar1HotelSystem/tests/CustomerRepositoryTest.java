package ca.mcgill.ecse321.Mar1HotelSystem.tests

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.CustomerRepository;

/**
 * This is the test class for the customer repository.
 *
 * @Author Liu, ZiXu
 */
@SprintBootTest
public class CustomerRepositoryTest {
    //Setting up the customer repository
    @Autowired
    private CustomerRepository customerRepository;

    //Clearing the database after the test
    @AfterEach
    public void clearDatabase() {
        customerRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadCustomer() {
        //Creation of a customer
        String firstName = 'Bob';
        String lastName = 'Lennon';
        String email = 'boblennon@gmail.com';
        int phoneNumber = 5146697031;
        String password = 'IamBob';
        Customer customer = new Customer(firstName, lastName, email, phoneNumber, password);

        //Adding the customer to the persistence layer
        customerRepository.save(customer);

        //Read from the database
        customer = customerRepository.findCustomerByEmail(email);

        //Assert that the customer is not null and that the registered data are correct
        assertNotNull(customer);
        assertEquals(firstName, customer.getFirstName());
        assertEquals(lastName, customer.getLastName());
        assertEquals(email, customer.getEmail());
        assertEquals(phoneNumber, customer.getPhoneNumber());
        assertEquals(password, customer.getPassword());
    }

}

