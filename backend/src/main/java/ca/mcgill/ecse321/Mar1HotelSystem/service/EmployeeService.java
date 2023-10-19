package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import jakarta.transaction.Transactional;

/**
 * This Service class is for the Customer entity, not customer service ~
 * 
 * @author Antoine Phan (@notkaramel)
 * @author Lucas Pacicco (@Lucaspac5)
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
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        return employee;
    }

    @Transactional
    public Employee createEmployees(String firstName, String lastName, String email, int phoneNumber, String password, int hoursWorked) {
        Employee employee = new Employee(firstName, lastName, email, phoneNumber, password, hoursWorked);
        employeeRepository.save(employee);
        return employee;
    }

    @Transactional
    public Employee deleteEmployee(String email) {
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        employeeRepository.delete(employee);
        return employee;
    }
}
