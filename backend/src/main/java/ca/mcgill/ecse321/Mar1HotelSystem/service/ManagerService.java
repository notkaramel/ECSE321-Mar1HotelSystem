package ca.mcgill.ecse321.Mar1HotelSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.ManagerRepository;
import jakarta.transaction.Transactional;

import java.util.List;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;

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

    @Transactional
    public List<Manager> getAllManagers() {
        return ServiceUtils.toList(managerRepository.findAll());
    }

    @Transactional
    public Manager getManager(String email) {
        Manager manager = managerRepository.findManagerByEmail(email);
        return manager;
    }

    @Transactional
    public Manager createManager(String firstName, String lastName, String email, int phoneNumber, String password) {
        Manager manager = new Manager(firstName, lastName, email, phoneNumber, password);
        managerRepository.save(manager);
        return manager;
    }

    @Transactional
    public Manager deleteManager(String email) {
        Manager manager = managerRepository.findManagerByEmail(email);
        managerRepository.delete(manager);
        return manager;
    }

    
}
