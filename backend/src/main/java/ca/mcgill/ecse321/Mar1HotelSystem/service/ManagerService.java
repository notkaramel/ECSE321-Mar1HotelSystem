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
        if(firstName == null || lastName == null || email == null || password == null){
            throw new IllegalArgumentException("Inputs invaild null");
        }
        else if(firstName == "" || lastName == "" || email == "" || password == ""){
            throw new IllegalArgumentException("One or more fields are empty");
        } 
        else {
            Pattern patternAt = Pattern.compile("@");
            Matcher matcherAt = patternAt.matcher(email);
            boolean matchFoundAt = matcherAt.find();
            Pattern patternDot = Pattern.compile(".", Pattern.LITERAL);
            Matcher matcherDot = patternDot.matcher(email);
            boolean matchFoundDot = matcherDot.find();
            if(matchFoundAt == false || matchFoundDot == false){
                throw new IllegalArgumentException("Invalid Email");
            }

            Manager manager = new Manager(firstName, lastName, email, phoneNumber, password);
            managerRepository.save(manager);
            return manager;
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
