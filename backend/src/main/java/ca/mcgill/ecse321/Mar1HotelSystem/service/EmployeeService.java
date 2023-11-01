package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.EmployeeRepository;
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

    @Transactional
    public List<Employee> getAllEmployees() {
        return ServiceUtils.toList(employeeRepository.findAll());
    }

    @Transactional
    public Employee getEmployee(String email) {
        String emailTrimmed = email.trim();
        return employeeRepository.findEmployeeByEmail(email);
    }

    @Transactional
    public Employee createEmployee(String firstName, String lastName, String email, long phoneNumber, String password,
            int hoursWorked) {
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
        // Create, save, and return the employee
        Employee employee = new Employee(firstName, lastName, email, phoneNumber, password, hoursWorked);
        employeeRepository.save(employee);
        return employee;
    }

    @Transactional
    public boolean updateEmployeeFirstName(String newFirstName, String email) {
        	Employee employee = getEmployee(email);
            // Check if firstName is empty
            if (newFirstName == null || newFirstName.trim().isEmpty()) {
                throw new IllegalArgumentException("The first name cannot be empty!");
            }
        	try {
        		employee.setFistName(newFirstName.trim());
                employeeRepository.save(employee);
        	} catch (Exception e) {
        		return false;
        	}
        	return true;
    }

    @Transactional
    public boolean deleteEmployee(String email) {
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        try {
            employeeRepository.delete(employee);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
