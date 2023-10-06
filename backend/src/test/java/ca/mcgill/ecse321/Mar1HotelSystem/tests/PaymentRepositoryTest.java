package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;

public class PaymentRepositoryTest {
    // Setting up the payment repository
    @Autowired
    private PaymentRepository paymentRepository;

    // Clearing the database after the test
    @AfterEach
    public void clearDatabase() {
        paymentRepository.deleteAll();
    }

    @Test
    public void testPersistAndReadPayment() {
        // Creating the payment
        int amount = 5;
        int id = 1;
        Payment payment = new Payment(amount, id);

        // Adding the payment to the persistence layer
        paymentRepository.save(payment);

        // Read from the database
        payment = paymentRepository.findPaymentByPaymentId(id);

        // Asserting the infog
        assertNotNull(payment);
        assertEquals(amount, payment.getAmount());
        assertEquals(id, payment.getPaymentId());
    }
}