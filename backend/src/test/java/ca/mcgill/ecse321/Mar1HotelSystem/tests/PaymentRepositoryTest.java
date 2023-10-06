package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;

/**
 * This is the testing class for the payment repository
 *
 * @author ZiXu Liu
 */
@SpringBootTest
public class PaymentRepositoryTest {
    // Setting up the payment repository
    @Autowired
    private PaymentRepository paymentRepository;

    // Clearing the database after the test
    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        paymentRepository.deleteAll();
    }

    // Main test for the payment repository
    @Test
    public void testPersistAndReadPayment() {
        // Creating the payment
        int amount = 5;
        Payment payment = new Payment(amount);        

        // Adding the payment to the persistence layer
        paymentRepository.save(payment);
        int paymentId = payment.getPaymentId();

        // Read from the database
        
        payment = paymentRepository.findPaymentByPaymentId(paymentId);

        // Assertions
        assertNotNull(payment);
        assertEquals(amount, payment.getAmount());
        assertEquals(paymentId, payment.getPaymentId());
    }
}