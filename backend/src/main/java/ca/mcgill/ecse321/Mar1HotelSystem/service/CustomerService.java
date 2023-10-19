package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.CustomerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;
import jakarta.transaction.Transactional;

/**
 * This Service class is for the Customer entity, not customer service ~
 * 
 * @author Antoine Phan (@notkaramel)
 */
@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public List<Customer> getAllCustomers() {
        return ServiceUtils.toList(customerRepository.findAll());
    }
}
