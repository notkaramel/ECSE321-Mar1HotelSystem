package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
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
    private PaymentRepository paymentRepository;

    @Transactional
    public Payment createPayment(int amount) {
        Payment newPayment = new Payment();
        newPayment.setAmount(amount);
        paymentRepository.save(newPayment);
        return newPayment;
    }

    @Transactional
    public Payment getPaymentById(int paymentId) {
       Payment payment = paymentRepository.findPaymentByPaymentId(paymentId);
        if(payment == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Payment with id " + paymentId + " does not exist.");
        }
        return payment;
    }

    @Transactional
    public List<Payment> getAllPayments() {
        return ServiceUtils.toList(paymentRepository.findAll());
    }

    @Transactional
    public void deletePaymentById(int paymentId) {
        Payment payment = this.getPaymentById(paymentId);
        paymentRepository.delete(payment);
        
    }

    @Transactional 
    public void updatePayment(int paymentId, int amount) {
        Payment payment = this.getPaymentById(paymentId);
        payment.setAmount(amount);
        paymentRepository.save(payment);
    }
}
