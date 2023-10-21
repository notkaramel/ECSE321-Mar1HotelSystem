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
 * @author Lucas Pacicco (@Lucaspac5)
 * 
 */
@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public List<Customer> getAllCustomers() {
        return ServiceUtils.toList(customerRepository.findAll());
    }

    @Transactional
    public Customer getCustomer(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        return customer;
    }

    @Transactional
    public Customer createCustomer(String firstName, String lastName, String email, int phoneNumber, String password) {
        Customer customer = new Customer(firstName, lastName, email, phoneNumber, password);
        customerRepository.save(customer);
        return customer;
    }

    @Transactional
    public boolean deleteCustomer(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        try {
            customerRepository.delete(customer);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
