package ca.mcgill.ecse321.Mar1HotelSystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;

@SpringBootTest
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

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
    boolean isDeleted = paymentService.deletePaymentById(1);
    assertTrue(isDeleted);
    verify(paymentRepository, times(1)).delete(any(Payment.class));
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
    public void testCreateInvalidPayment() {
        int paymentId = 0;
        Payment payment = new Payment();
        when(paymentRepository.findPaymentByPaymentId(paymentId)).thenReturn(payment);
        Exception e = assertThrows(RuntimeException.class, () -> paymentService.createPayment(0));
        assertEquals("Payment with id: " + paymentId + " already exists.", e.getMessage());
    }
    

}
