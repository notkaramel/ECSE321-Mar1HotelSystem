package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;

/**
 * Service class is for the GeneralUser (Guest).
 * It addresses all use cases of a Guest.
 * 
 * @author Antoine Phan (@notkaramel)
 */

@Service
public class GeneralUserService {
    @Autowired
    GeneralUserRepository generalUserRepository;

    public List<GeneralUser> getAllGeneralUsers() {
        return ServiceUtils.toList(generalUserRepository.findAll());
    }
}
