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
        Payment payment = new Payment(100);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
        Payment createdPayment = paymentService.createPayment(100);
        assertNotNull(createdPayment);
        assertEquals(100, createdPayment.getAmount());
    }

    @Test
    public void testGetPaymentById() {
        Payment payment = new Payment(100);
        when(paymentRepository.findPaymentByPaymentId(1)).thenReturn(payment);
        Payment foundPayment = paymentService.getPaymentById(1);
        assertNotNull(foundPayment);
        assertEquals(100, foundPayment.getAmount());
    }

    @Test
    public void testDeletePaymentById() {
        Payment payment = new Payment(100);
        payment.setPaymentId(1);
        when(paymentRepository.findPaymentByPaymentId(anyInt())).thenReturn(payment);
        doNothing().when(paymentRepository).delete(any(Payment.class));
        paymentService.deletePaymentById(1);
        List<Payment> payments = paymentService.getAllPayments();

        assertEquals(0, payments.size());

    }

    @Test
    public void testGetAllPayments() {
        Payment payment1 = new Payment(100);
        Payment payment2 = new Payment(200);
        when(paymentRepository.findAll()).thenReturn(List.of(payment1, payment2));

        List<Payment> payments = paymentService.getAllPayments();

        assertEquals(2, payments.size());
    }

    @Test
    public void testUpdatePayment() {
        Payment payment = new Payment(100);
        payment.setPaymentId(1);
        when(paymentRepository.findPaymentByPaymentId(anyInt())).thenReturn(payment);
        paymentService.updatePayment(1, 200);
        assertNotNull(payment);
        assertEquals(200, payment.getAmount());
    }

    @Test
    public void testInvalidDeletePaymentById(){
        when(paymentRepository.findPaymentByPaymentId(anyInt())).thenReturn(null);
        try {
            paymentService.deletePaymentById(1);
       } catch (Exception e) {
           assertThrows(Mar1HotelSystemException.class, () -> paymentService.deletePaymentById(1));
       }
    }

    @Test
    public void testInvalidGetPaymentById(){
        when(paymentRepository.findPaymentByPaymentId(anyInt())).thenReturn(null);
        try {
            paymentService.getPaymentById(1);
       } catch (Exception e) {
           assertThrows(Mar1HotelSystemException.class, () -> paymentService.getPaymentById(1));
       }
    }

}
