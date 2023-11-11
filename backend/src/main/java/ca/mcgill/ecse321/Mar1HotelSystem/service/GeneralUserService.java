package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.AccountRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.CustomerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.ManagerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import jakarta.transaction.Transactional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service class is for the GeneralUser (Guest).
 * It addresses all use cases of a Guest.
 * 
 * @author Antoine Phan (@notkaramel)
 * @author Lucas Pacicco (@Lucaspac5)
 * @author ZiXu Liu (@ARandomPi)
 */

@Service
public class GeneralUserService {
    @Autowired
    GeneralUserRepository generalUserRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public List<GeneralUser> getAllGeneralUsers() {
        List<GeneralUser> generalUserList = generalUserRepository.findAll();
        // Check if general user list is null or empty
        if (generalUserList == null || generalUserList.size() == 0) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "There are no Users found!");
        } else {
            return generalUserList;
        }
    }

    @Transactional
    public GeneralUser getGeneralUser(String email) {
        GeneralUser generalUser = generalUserRepository.findGeneralUserByEmail(email);
        // Check if general user not found
        if (generalUser == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "User Not Found");
        } // Check if email is empty
        else if (email == null || email.trim().isEmpty()) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The email cannot be empty!");
        } else {
            return generalUser;
        }
    }

    /**
     * Creates a GeneralUser with the given parameters.
     * 
     * @param firstName   The first name of the GeneralUser.
     * @param lastName    The last name of the GeneralUser.
     * @param email       The email of the GeneralUser.
     * @param phoneNumber The phone number of the GeneralUser.
     * @return The created GeneralUser.
     * @author ZiXu Liu (@ARandomPi)
     */
    @Transactional
    public GeneralUser createGeneralUser(String firstName, String lastName, String email, long phoneNumber) {
        // Check is all inputs are null
        if (firstName == null && lastName == null && email == null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "All inputs null!");
        }
        // Check is all inputs are inputes
        else if (firstName.trim().isEmpty() && lastName.trim().isEmpty() && email.trim().isEmpty()) {
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
        // Check if user already exists
        else if (generalUserRepository.findGeneralUserByEmail(email) != null
                || managerRepository.findManagerByEmail(email) != null
                || customerRepository.findCustomerByEmail(email) != null
                || employeeRepository.findEmployeeByEmail(email) != null
                || accountRepository.findAccountByEmail(email) != null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "User with that email already exists!");
        } else {
            String emailTrimmed = email.trim();
            Pattern pattern = Pattern.compile("^(\\S+)@(\\S+)\\.((com)|(ca))$");
            Matcher matcher = pattern.matcher(emailTrimmed);
            // Check if email valid format
            if (!matcher.find()) {
                throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The email is invalid!");
            } else {
                // Create general user
                GeneralUser generalUser = new GeneralUser(firstName, lastName, email, phoneNumber);
                generalUserRepository.save(generalUser);
                return generalUser;
            }
        }

    }

    @Transactional
    public GeneralUser updateGeneralUserEmail(String oldEmail, String newEmail) {
        GeneralUser generalUser = generalUserRepository.findGeneralUserByEmail(oldEmail);
        // Check if user not found
        if (generalUser == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "User Not Found");
        }
        // Check if user already has new email
        else if (generalUserRepository.findGeneralUserByEmail(newEmail) != null
                || managerRepository.findManagerByEmail(newEmail) != null
                || customerRepository.findCustomerByEmail(newEmail) != null
                || employeeRepository.findEmployeeByEmail(newEmail) != null
                || accountRepository.findAccountByEmail(newEmail) != null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "User with that email already exists!");
        } else {
            String emailTrimmed = newEmail.trim();
            Pattern pattern = Pattern.compile("^(\\S+)@(\\S+)\\.((com)|(ca))$");
            Matcher matcher = pattern.matcher(emailTrimmed);
            // Check if new email is valid format
            if (!matcher.find()) {
                throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The new email is invalid!");
            }
            // Check if email is empty
            else if (newEmail == null || newEmail.trim().isEmpty()) {
                throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "The email cannot be empty!");
            } else {
                generalUser.setEmail(newEmail);
                generalUserRepository.save(generalUser);
                return generalUser;
            }
        }
    }

    @Transactional
    public boolean deleteGeneralUser(String email) {
        GeneralUser generalUser = generalUserRepository.findGeneralUserByEmail(email);
        try {
            // Check if general user
            if (generalUserRepository.findGeneralUserByEmail(email) == null) {
                throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "User with that email does not exist!");
            } else {
                // Delete general user
                generalUserRepository.delete(generalUser);
                return true;
            }
        } catch (Exception e) {
            return false;
        }

    }
}
