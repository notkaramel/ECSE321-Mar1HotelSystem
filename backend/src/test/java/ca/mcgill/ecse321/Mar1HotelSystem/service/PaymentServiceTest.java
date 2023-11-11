package ca.mcgill.ecse321.Mar1HotelSystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;

@SpringBootTest
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        paymentRepository.deleteAll();
    }

    @Test
    public void testCreatePayment() {
        try {
            Payment payment = new Payment(100);
            when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
            Payment createdPayment = paymentService.createPayment(100);
            assertNotNull(createdPayment);
            assertEquals(100, createdPayment.getAmount());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGetPaymentById() {
        try {
            Payment payment = new Payment(100);
            when(paymentRepository.findPaymentByPaymentId(1)).thenReturn(payment);
            Payment foundPayment = paymentService.getPaymentById(1);
            assertNotNull(foundPayment);
            assertEquals(100, foundPayment.getAmount());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDeletePaymentById() {
        try {
            Payment payment = new Payment(100);
            payment.setPaymentId(1);
            when(paymentRepository.findPaymentByPaymentId(anyInt())).thenReturn(payment);
            doNothing().when(paymentRepository).delete(any(Payment.class));
            paymentService.deletePaymentById(1);
            List<Payment> payments = paymentService.getAllPayments();

            assertEquals(0, payments.size());

        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void testGetAllPayments() {
        try {
            Payment payment1 = new Payment(100);
            Payment payment2 = new Payment(200);
            when(paymentRepository.findAll()).thenReturn(List.of(payment1, payment2));

            List<Payment> payments = paymentService.getAllPayments();
            assertEquals(2, payments.size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testUpdatePayment() {
        try {
            Payment payment = new Payment(100);
            payment.setPaymentId(1);
            when(paymentRepository.findPaymentByPaymentId(anyInt())).thenReturn(payment);
            paymentService.updatePayment(1, 200);
            assertNotNull(payment);
            assertEquals(200, payment.getAmount());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testInvalidDeletePaymentById() {
        Mar1HotelSystemException error = null;
        when(paymentRepository.findPaymentByPaymentId(anyInt())).thenReturn(null);
        try {
            paymentService.deletePaymentById(132);
        } catch (Mar1HotelSystemException e) {
            error = e;
        }
        assertEquals(HttpStatus.NOT_FOUND, error.getStatus());
        assertEquals("Payment with id 132 does not exist.", error.getMessage());
    }

    @Test
    public void testInvalidGetPaymentById() {
        Mar1HotelSystemException error = null;
        when(paymentRepository.findPaymentByPaymentId(anyInt())).thenReturn(null);
        try {
            paymentService.getPaymentById(1);
        } catch (Mar1HotelSystemException e) {
            error = e;
        }
        assertEquals(HttpStatus.NOT_FOUND, error.getStatus());
        assertEquals("Payment with id 1 does not exist.", error.getMessage());
    }

}
