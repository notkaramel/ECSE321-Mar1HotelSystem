package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import jakarta.transaction.Transactional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        List<GeneralUser> generalUserList = ServiceUtils.toList(generalUserRepository.findAll());
        if(generalUserList == null || generalUserList.size() == 0){
            throw new IllegalArgumentException("There are no Users found!");
        } else {
            return generalUserList;
        }  
    }

    @Transactional
    public GeneralUser getGeneralUser(String email) {
        GeneralUser generalUser = generalUserRepository.findGeneralUserByEmail(email);
        if(generalUser == null){
            throw new IllegalArgumentException("User Not Found");
        } else {
            return generalUser;
        }
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
         //Check is all inputs are null
        if(firstName == null && lastName == null && email == null){
            throw new IllegalArgumentException("All inputs null!");
        }
        //Check is all inputs are inputes
        else if(firstName.trim().isEmpty() && lastName.trim().isEmpty() && email.trim().isEmpty()){
            throw new IllegalArgumentException("All fields are empty!");
        } 
        // Check if firstName is empty
        else if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("The first name cannot be empty!");
        }
        // Check if lastName is empty
        else if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("The last name cannot be empty!");
        }
        // Check if email is empty
        else if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("The email cannot be empty!");
        }
        else {
            String emailTrimmed = email.trim();
            Pattern pattern = Pattern.compile("^(\\S+)@(\\S+)\\.((com)|(ca))$");
            Matcher matcher = pattern.matcher(emailTrimmed);
            if (!matcher.find()) {
                throw new IllegalArgumentException("The email is invalid!");
            } else {

                GeneralUser generalUser = new GeneralUser(firstName, lastName, email, phoneNumber);
                generalUserRepository.save(generalUser);
                return generalUser;
        }
        }
       
    }

    @Transactional
    public GeneralUser updateGeneralUserEmail(String oldEmail, String newEmail) {
        GeneralUser generalUser = generalUserRepository.findGeneralUserByEmail(oldEmail);
        if(generalUser == null){
            throw new IllegalArgumentException("User Not Found");
        } else{
            generalUser.setEmail(newEmail);
            return generalUser;
        }
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
