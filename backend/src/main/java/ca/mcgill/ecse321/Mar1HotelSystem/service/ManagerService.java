package ca.mcgill.ecse321.Mar1HotelSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.ManagerRepository;
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

    // @Transactional
    // public List<Manager> getAllManagers() {
    //    List<Manager> managerList = ServiceUtils.toList(managerRepository.findAll());
    //    if(managerList == null){
    //         throw new IllegalArgumentException("There are no Managers Found");
    //     } else {
    //         return managerList;
    //     }
    // }

    @Transactional
    public Manager getManager(String email) {
        Manager manager = managerRepository.findManagerByEmail(email);
        if(manager == null){
            throw new IllegalArgumentException("Manager Not Found");
        } else {
            return manager;
        }
        
    }

    @Transactional
    public Manager createManager(String firstName, String lastName, String email, int phoneNumber, String password){
        //Check is all inputs are null
        if(firstName == null && lastName == null && email == null && password == null){
            throw new IllegalArgumentException("All inputs null!");
        }
        //Check is all inputs are inputes
        else if(firstName.trim().isEmpty() && lastName.trim().isEmpty() && email.trim().isEmpty() && password.trim().isEmpty()){
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
        // Check if password is empty
        else if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("The password cannot be empty!");
        }
        else {
            String emailTrimmed = email.trim();
            Pattern pattern = Pattern.compile("^(\\S+)@(\\S+)\\.((com)|(ca))$");
            Matcher matcher = pattern.matcher(emailTrimmed);
            if (!matcher.find()) {
                throw new IllegalArgumentException("The email is invalid!");
            } else {
            Manager manager = new Manager(firstName, lastName, email, phoneNumber, password);
            managerRepository.save(manager);
            return manager;
        }
        }
        
    }

    @Transactional
    public boolean deleteManager(String email) {
        Manager manager = managerRepository.findManagerByEmail(email);
        try {
            managerRepository.delete(manager);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
