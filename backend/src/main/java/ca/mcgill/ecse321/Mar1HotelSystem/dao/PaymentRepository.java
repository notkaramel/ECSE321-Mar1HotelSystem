package ca.mcgill.ecse321.Mar1HotelSystem.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, String>{
    Payment findPaymentByPaymentID(int paymentID);
}
