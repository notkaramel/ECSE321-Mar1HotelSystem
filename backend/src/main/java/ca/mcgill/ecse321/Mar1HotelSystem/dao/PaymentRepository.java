package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;

/**
 * The CRUD Repository Interface to store and retrieve all Payment objects.
 * 
 * @author Antoine Phan (@notkaramel)
 */
public interface PaymentRepository extends CrudRepository<Payment, String> {
    /**
     * Find a Payment object by its paymentId.
     * 
     * @param paymentId
     * @return the corresponding Payment object
     */
    public Payment findPaymentByPaymentId(int paymentId);
}
