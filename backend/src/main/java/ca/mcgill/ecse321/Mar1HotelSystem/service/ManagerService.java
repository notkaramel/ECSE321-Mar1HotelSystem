package ca.mcgill.ecse321.Mar1HotelSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.ManagerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Serivce class for all use cases of a manager
 * 
 * @author Antoine Phan (@notkaramel)
 * @author Lucas Pacicco (@Lucaspac5)
 * 
 */

@Service
public class ManagerService {
    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    GeneralUserRepository generalUserRepository;

    @Transactional
    public List<Manager> getAllManagers() {
        List<Manager> managerList = ServiceUtils.toList(managerRepository.findAll());
        // Check if manager list null or empty
        if (managerList == null || managerList.size() == 0) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "There are no Managers Found");
        } else {
            return managerList;
        }
    }

    @Transactional
    public Manager getManager(String email) {
        Manager manager = managerRepository.findManagerByEmail(email);
        // Check if manager not found
        if (manager == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Manager Not Found");
        } // Check if email is empty
        else if (email == null || email.trim().isEmpty()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The email cannot be empty!");
        } else {
            return manager;
        }

    }

    @Transactional
    public Manager createManager(String firstName, String lastName, String email, long phoneNumber, String password) {
        // Check is all inputs are null
        if (firstName == null && lastName == null && email == null && password == null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "All inputs null!");
        }
        // Check is all inputs are inputes
        else if (firstName.trim().isEmpty() && lastName.trim().isEmpty() && email.trim().isEmpty()
                && password.trim().isEmpty()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "All fields are empty!");
        }
        // Check if firstName is empty
        else if (firstName == null || firstName.trim().isEmpty()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The first name cannot be empty!");
        }
        // Check if lastName is empty
        else if (lastName == null || lastName.trim().isEmpty()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The last name cannot be empty!");
        }
        // Check if email is empty
        else if (email == null || email.trim().isEmpty()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The email cannot be empty!");
        }
        // Check if password is empty
        else if (password == null || password.trim().isEmpty()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The password cannot be empty!");
        }
        // Check if user already exists
        else if (generalUserRepository.findGeneralUserByEmail(email) != null
                || managerRepository.findManagerByEmail(email) != null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "User with that email already exists!");
        } else {
            String emailTrimmed = email.trim();
            Pattern pattern = Pattern.compile("^(\\S+)@(\\S+)\\.((com)|(ca))$");
            Matcher matcher = pattern.matcher(emailTrimmed);
            // Check if email is valid format
            if (!matcher.find()) {
                throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The email is invalid!");
            } else {
                // Create manager
                Manager manager = new Manager(firstName, lastName, email, phoneNumber, password);
                managerRepository.save(manager);
                return manager;
            }
        }

    }

    @Transactional
    public Manager updateManagerPassword(String email, String oldPassword, String newPassword) {
        Manager manager = managerRepository.findManagerByEmail(email);
        // Check if user exists
        if (manager == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "User Not Found");
        } // Check if password is empty
        else if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The password cannot be empty!");
        } else {
            if (manager.getPassword().equals(oldPassword)) {
                manager.setPassword(newPassword);
                return manager;
            } else {
                throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Incorrect old password!");
            }
        }
    }

    @Transactional
    public boolean deleteManager(String email) {
        Manager manager = managerRepository.findManagerByEmail(email);
        try {
            // Check if user exists
            if (managerRepository.findManagerByEmail(email) == null) {
                throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "User with that email does not exist!");
            } else {
                // Delete manager
                managerRepository.delete(manager);
                return true;
            }
        } catch (Exception e) {
            return false;
        }

    }
}
