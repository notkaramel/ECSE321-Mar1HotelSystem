package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import jakarta.transaction.Transactional;
/**
 * Service class/methods for the Payment features
 * 
 * @author Antoine Phan (@notkaramel)
 */
@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Transactional
    public Payment createPayment(int amount) {
        Payment newPayment = new Payment();
        newPayment.setAmount(amount);
        paymentRepository.save(newPayment);
        return newPayment;
    }

    @Transactional
    public Payment getPaymentById(int paymentId) {
        return paymentRepository.findPaymentByPaymentId(paymentId);
    }

    @Transactional
    public List<Payment> getAllPayments() {
        return ServiceUtils.toList(paymentRepository.findAll());
    }

    @Transactional
    public boolean deletePaymentById(int paymentId) {
        Payment payment = paymentRepository.findPaymentByPaymentId(paymentId);
        try {
            paymentRepository.delete(payment);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
