package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.CustomerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerDao;

    @InjectMocks
    private CustomerService customerService;

    private static final String CUSTOMER_KEY = "TestEmail";
    private static final String NONEXISTING_KEY = "NotACustomer";

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


}
