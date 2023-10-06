package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;

/**
 * The CRUD Repository Interface to store and retrieve all Customer objects.
 * 
 * @author Antoine Phan (@notkaramel)
 */
public interface CustomerRepository extends CrudRepository<Customer, String> {
    /**
     * Find a Customer object by their email address.
     * 
     * @param email
     * @return the corresponding Customer object to the email address
     */
    public Customer findCustomerByEmail(String email);
}
