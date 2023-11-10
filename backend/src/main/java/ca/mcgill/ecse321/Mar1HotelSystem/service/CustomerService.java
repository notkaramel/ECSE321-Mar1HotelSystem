package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.*;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    EmployeeRepository employeeRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    GeneralUserRepository generalUserRepository;

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
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The first name cannot be empty!");
        }
        // Check if lastName is empty
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The last name cannot be empty!");
        }
        // Check if email is empty
        if (email == null || email.trim().isEmpty()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The email cannot be empty!");
        }
        // Check if email is valid
        String emailTrimmed = email.trim();
        Pattern pattern = Pattern.compile("^(\\S+)@(\\S+)\\.((com)|(ca))$");
        Matcher matcher = pattern.matcher(emailTrimmed);
        if (!matcher.find()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The email is invalid!");
        }
        // Check if email is already used
        // Note: why must customer be fore employee?
        if(customerRepository.findCustomerByEmail(emailTrimmed) != null
                || employeeRepository.findEmployeeByEmail(emailTrimmed) != null
                || managerRepository.findManagerByEmail(emailTrimmed) != null
                || generalUserRepository.findGeneralUserByEmail(emailTrimmed) != null
                || accountRepository.findAccountByEmail(emailTrimmed) != null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "User using that email already exists!");
        }
        // Check if the phone number is above 0
        if (phoneNumber <= 0) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The phone number must be above 0!");
        }
        // Check if password is empty
        if (password == null || password.isEmpty()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The password cannot be empty!");
        }
        // Create, save, and return the customer
        Customer customer = new Customer(firstName, lastName, email, phoneNumber, password);
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer updateCustomer(String newFirstName, String newLastName, long newPhoneNumber, String newPassword, String email) {
        // Check if firstName is empty
        if (newFirstName == null || newFirstName.trim().isEmpty()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The first name cannot be empty!");
        }
        // Check if lastName is empty
        if (newLastName == null || newLastName.trim().isEmpty()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The last name cannot be empty!");
        }
        // Check if email is empty
        if (email == null || email.trim().isEmpty()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The email cannot be empty!");
        }
        // Check if email is valid
        String emailTrimmed = email.trim();
        Pattern pattern = Pattern.compile("^(\\S+)@(\\S+)\\.((com)|(ca))$");
        Matcher matcher = pattern.matcher(emailTrimmed);
        if (!matcher.find()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The email is invalid!");
        }
        // Check if the phone number is above 0
        if (newPhoneNumber <= 0) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The phone number must be above 0!");
        }
        // Check if password is empty
        if (newPassword == null || newPassword.isEmpty() ) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The password cannot be empty!");
        }
        // Get Customer
        Customer customer = getCustomer(emailTrimmed);
        if (customer == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "The customer does not exist!");
        }
        customer.setFirstName(newFirstName.trim());
        customer.setLastName(newLastName.trim());
        customer.setPhoneNumber(newPhoneNumber);
        customer.setPassword(newPassword);
        return customerRepository.save(customer);
    }

    @Transactional
    public boolean deleteCustomer(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "The customer does not exist!");
        }
        customerRepository.delete(customer);
        return true;
    }

}
