package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.*;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import jakarta.transaction.Transactional;

/**
 * This Service class is for the Customer entity, not customer service ~
 * 
 * @author Antoine Phan (@notkaramel)
 * @author Lucas Pacicco (@Lucaspac5)
 * @author ZiXu Liu (@ARandomPi)
 * 
 */

@Service
public class EmployeeService {
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
    public List<Employee> getAllEmployees() {
        return ServiceUtils.toList(employeeRepository.findAll());
    }

    @Transactional
    public Employee getEmployee(String email) {
        String emailTrimmed = email.trim();
        return employeeRepository.findEmployeeByEmail(emailTrimmed);
    }

    @Transactional
    public Employee createEmployee(String firstName, String lastName, String email, long phoneNumber, String password,
            int hoursWorked) {
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
        if (employeeRepository.findEmployeeByEmail(emailTrimmed) != null
                || customerRepository.findCustomerByEmail(emailTrimmed) != null
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
        if (password == null || password.trim().isEmpty()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The password cannot be empty!");
        }
        // Create, save, and return the employee
        Employee employee = new Employee(
                firstName.trim(),
                lastName.trim(),
                emailTrimmed,
                phoneNumber,
                password,
                hoursWorked);
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(String newFirstName, String newLastName, long newPhoneNumber, String newPassword, String email) {
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
            if (newPhoneNumber <= 0) {
                throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The phone number must be above 0!");
            }
            // Check if password is empty
            if (newPassword == null || newPassword.trim().isEmpty()) {
                throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The password cannot be empty!");
            }
            // Getting the employee
            Employee employee = getEmployee(emailTrimmed);
            if (employee == null) {
                throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "The employee does not exist!");
            }
            // Updating the employee
            employee.setFirstName(newFirstName.trim());
            employee.setLastName(newLastName.trim());
            employee.setPhoneNumber(newPhoneNumber);
            employee.setPassword(newPassword);
            return employeeRepository.save(employee);
    }

    @Transactional
    public boolean deleteEmployee(String email) {
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "The employee does not exist!");
        }
        employeeRepository.delete(employee);
        return true;
    }
}
