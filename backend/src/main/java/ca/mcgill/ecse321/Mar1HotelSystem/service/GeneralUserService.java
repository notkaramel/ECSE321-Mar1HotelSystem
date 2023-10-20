package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import jakarta.transaction.Transactional;

/**
 * Service class is for the GeneralUser (Guest).
 * It addresses all use cases of a Guest.
 * 
 * @author Antoine Phan (@notkaramel)
 * @author Lucas Pacicco (@Lucaspac5)
 * 
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
        GeneralUser generalUser = generalUserRepository.findGeneralUserByEmail(email);
        return generalUser;
    }

    @Transactional
    public GeneralUser createGeneralUser(String firstName, String lastName, String email, int phoneNumber) {
        GeneralUser generalUser = new GeneralUser(firstName, lastName, email, phoneNumber);
        generalUserRepository.save(generalUser);
        return generalUser;
    }

    @Transactional
    public boolean deleteGeneralUser(String email) {
        GeneralUser generalUser = generalUserRepository.findGeneralUserByEmail(email);
        if (generalUser == null) {
            return false;
        }
        generalUserRepository.delete(generalUser);
        return true;
    }
}
