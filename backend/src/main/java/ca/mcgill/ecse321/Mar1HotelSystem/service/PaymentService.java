package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import jakarta.transaction.Transactional;

/**
 * This Service class is for the Customer entity, not customer service ~
 * 
 * @author Antoine Phan (@notkaramel)
 * @author Lucas Pacicco (@Lucaspac5)
 * 
 */

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Transactional
    public List<Payment> getAllPayments() {
        return ServiceUtils.toList(paymentRepository.findAll());
    }

    @Transactional
    public Payment getPayment(int paymentId) {
        Payment payment = paymentRepository.findPaymentByPaymentId(paymentId);
        return payment;
    }

    @Transactional
    public Payment createPayment(int amount) {
        Payment payment = new Payment(amount);
        paymentRepository.save(payment);
        return payment;
    }

    @Transactional
    public Payment deletePayment(int paymentId) {
        Payment payment = paymentRepository.findPaymentByPaymentId(paymentId);
        paymentRepository.delete(payment);
        return payment;
    }
}
