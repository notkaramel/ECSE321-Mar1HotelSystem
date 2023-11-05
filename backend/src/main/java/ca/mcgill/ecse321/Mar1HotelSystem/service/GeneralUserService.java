package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import jakarta.transaction.Transactional;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

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

    @Transactional
    public List<GeneralUser> getAllGeneralUsers() {
        return ServiceUtils.toList(generalUserRepository.findAll());
    }

    @Transactional
    public GeneralUser getGeneralUser(String email) {
        String emailTrimmed = email.trim();
        return generalUserRepository.findGeneralUserByEmail(emailTrimmed);
    }

    /**
     * Creates a GeneralUser with the given parameters.
     * @param firstName The first name of the GeneralUser.
     * @param lastName The last name of the GeneralUser.
     * @param email The email of the GeneralUser.
     * @param phoneNumber The phone number of the GeneralUser.
     * @return The created GeneralUser.
     * @author ZiXu Liu (@ARandomPi)
     */
    @Transactional
    public GeneralUser createGeneralUser(String firstName, String lastName, String email, long phoneNumber) {
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
        // Create, save and return the GeneralUser
        GeneralUser generalUser = new GeneralUser(firstName.trim(), lastName.trim(), emailTrimmed, phoneNumber);
        generalUserRepository.save(generalUser);
        return generalUser;
    }

    @Transactional
    public boolean deleteGeneralUser(String email) {
        GeneralUser generalUser = generalUserRepository.findGeneralUserByEmail(email);
        try {
            generalUserRepository.delete(generalUser);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}