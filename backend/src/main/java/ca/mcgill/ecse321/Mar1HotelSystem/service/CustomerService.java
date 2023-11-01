package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.CustomerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;
import jakarta.transaction.Transactional;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This Service class is for the Customer entity, not customer service ~
 * 
 * @author Antoine Phan (@notkaramel)
 * @author Lucas Pacicco (@Lucaspac5)
 * @author ZiXu Liu (@ARandomPi)
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
        String emailTrimmed = email.trim();
        return customerRepository.findCustomerByEmail(emailTrimmed);
    }

    @Transactional
    public Customer createCustomer(String firstName, String lastName, String email, long phoneNumber, String password) {
        // Check if firstName is empty
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("The first name cannot be empty!");
        }
        // Check if lastName is empty
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("The last name cannot be empty!");
        }
        // Check if email is empty
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("The email cannot be empty!");
        }
        // Check if email is valid
        String emailTrimmed = email.trim();
        Pattern pattern = Pattern.compile("^(\\S+)@(\\S+)\\.((com)|(ca))$");
        Matcher matcher = pattern.matcher(emailTrimmed);
        if (!matcher.find()) {
            throw new IllegalArgumentException("The email is invalid!");
        }
        // Check if password is empty
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("The password cannot be empty!");
        }
        // Create, save, and return the customer
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
