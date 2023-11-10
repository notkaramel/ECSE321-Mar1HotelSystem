package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.*;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import jakarta.transaction.Transactional;

import javax.xml.crypto.Data;

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

    @Autowired
    ShiftRepository shiftRepository;

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
        }// Check if the hours worked is not negative
        if (hoursWorked < 0) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The hours worked must not be negative!");
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
    public Employee updateEmployee(String newFirstName, String newLastName, String email, long newPhoneNumber, String newPassword, int newHoursWorked) {
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
            // Check if the hours worked is not negative
            if (newHoursWorked < 0) {
                throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The hours worked must not be negative!");
            }
            // Updating the employee
            employee.setFirstName(newFirstName.trim());
            employee.setLastName(newLastName.trim());
            employee.setPhoneNumber(newPhoneNumber);
            employee.setPassword(newPassword);
            employee.setHoursWorked(newHoursWorked);
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

    @Transactional
    public List<Shift> getAllShifts() {
        return ServiceUtils.toList(shiftRepository.findAll());
    }
    @Transactional
    public List<Shift> getShiftsEmployee(String email) {
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        // Check if the employee exists
        if (employee == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "The employee does not exist!");
        }
        // Get all shifts
        ArrayList<Shift> shifts = (ArrayList<Shift>) shiftRepository.findAll();
        ArrayList<Shift> employeeShifts = new ArrayList<Shift>();
        // Filter the shifts to only the ones that belong to the employee
        for (Shift s: shifts) {
            if (Objects.equals(s.getEmployee().getEmail(), email)) {
                employeeShifts.add(s);
            }
        }
        return employeeShifts;
    }

    @Transactional
    public Shift getShift(int id) {
        for (Shift s: shiftRepository.findAll()) {
            System.out.println(s.getShiftId() + " " + id);
        }
        return shiftRepository.findShiftByShiftId(id);
    }

    @Transactional
    public Shift createShift(Date date, int startTime, int endTime, String email) {
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        // Check if the employee exists
        if (employee == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "The employee does not exist!");
        }
        // Check if the start date is between 0 and 24 hours
        if (startTime < 0 || startTime >= 24) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The start time must be between 0 and 24!");
        }
        // Check if the end date is between 0 and 24 hours
        if (endTime < 0 || endTime >= 24) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The end time must be between 0 and 24!");
        }
        // Check if the end date is after the start date
        if (endTime < startTime) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The end time must be after the start time!");
        }
        // Create the shift
        Shift shift = new Shift(employee, date, startTime, endTime);
        return shiftRepository.save(shift);
    }

    @Transactional
    public Shift updateShift(int id, Date newDate, int newStartTime, int newEndTime) {
        Shift shift = shiftRepository.findShiftByShiftId(id);
        // Check if the shift exists
        if (shift == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "The shift does not exist!");
        }
        // Check if the start date is between 0 and 24 hours
        if (newStartTime < 0 || newStartTime >= 24) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The start time must be between 0 and 24!");
        }
        // Check if the end date is between 0 and 24 hours
        if (newEndTime < 0 || newEndTime >= 24) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The end time must be between 0 and 24!");
        }
        // Check if the end date is after the start date
        if (newEndTime < newStartTime) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The end time must be after the start time!");
        }
        // Update the shift
        shift.setDate(newDate);
        shift.setStartTime(newStartTime);
        shift.setEndTime(newEndTime);
        return shiftRepository.save(shift);
    }

    @Transactional
    public boolean deleteShift(int shiftId) {
        Shift shift = shiftRepository.findShiftByShiftId(shiftId);
        if (shift == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "The shift does not exist!");
        }
        shiftRepository.delete(shift);
        return true;
    }
}
