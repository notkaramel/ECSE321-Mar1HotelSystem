package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {
    public Customer findCustomerByEmail(String email);
}
