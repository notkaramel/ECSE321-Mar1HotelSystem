package ca.mcgill.ecse321.Mar1HotelSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import jakarta.transaction.Transactional;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;

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
}
