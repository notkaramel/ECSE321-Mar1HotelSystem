package ca.mcgill.ecse321.Mar1HotelSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.ManagerRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;

/**
 * Serivce class for all use cases of a manager
 * 
 * @author Antoine Phan (@notkaramel)
 */

@Service
public class ManagerService {
    @Autowired
    ManagerRepository managerRepository;

    @Transactional
    public List<Manager> getAllManagers() {
        return ServiceUtils.toList(managerRepository.findAll());
    }
}
