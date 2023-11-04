package ca.mcgill.ecse321.Mar1HotelSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.ManagerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;

import jakarta.transaction.Transactional;

import java.util.List;

import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
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
       if(managerList == null || managerList.size() == 0){
            throw new IllegalArgumentException("There are no Managers Found");
        } else {
            return managerList;
        }
    }

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
        else if(generalUserRepository.findGeneralUserByEmail(email) != null){
            throw new IllegalArgumentException("User with that email already exists!");
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
    public Manager updateManagerPassword(String email, String oldPassword, String newPassword) {
        Manager manager = managerRepository.findManagerByEmail(email);
        if(manager == null){
            throw new IllegalArgumentException("User Not Found");
        } 

         else{
            if(manager.getPassword() == oldPassword){
            manager.setPassword(newPassword);
            return manager;
            } else {
                throw new IllegalArgumentException("Incorrect old password!");
            }
        }
    }

    @Transactional
    public boolean deleteManager(String email) {
        Manager manager = managerRepository.findManagerByEmail(email);
        try {
            if(managerRepository.findManagerByEmail(email) == null){
                throw new IllegalArgumentException("User with that email does not exist!");
        } 
        // else if(managerRepository.delete(manager) = null){
        //     throw new IllegalArgumentException("User with that email does not exist!");
        // }
            else {
            managerRepository.delete(manager);
            return true;
        }
        } catch (Exception e) {
            return false;
        }

}
}
