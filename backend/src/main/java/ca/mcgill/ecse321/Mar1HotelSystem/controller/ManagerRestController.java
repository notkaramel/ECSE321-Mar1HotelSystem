package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.ManagerDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;
import ca.mcgill.ecse321.Mar1HotelSystem.service.ManagerService;
import java.util.List;
import java.util.ArrayList;

/**
 * The controller that handles /manager endpoint requests
 * Required functionalities:
 * - Fire an employee (delete employee)
 * - etc.
 * 
 * @author Lucas Paccico @Lucaspac5
 */
@CrossOrigin(origins = "*")
@RestController
public class ManagerRestController {
    
    @Autowired
	private ManagerService service;
    
    @GetMapping(value = { "/managers", "/managers/" })
    public List<Manager> getAllManagers() {
        List<Manager> managers = new ArrayList<>();
        managers = service.getAllManagers();
        return managers;
    }

    @GetMapping(value = { "/managers/{email}", "/managers/{email}/" })
    public Manager getManager(@PathVariable("email") String email) {
        Manager manager = service.getManager(email);
        return manager;
    }

    @PostMapping(value = { "/managers/{email}/{firstName}/{lastName}/{phoneNumber}/{password}", "/managers/{email}/{firstName}/{lastName}/{phoneNumber}/{password}/" })
    public Manager createManager( @PathVariable("email") String email, @PathVariable("firstName") String firstName, 
    @PathVariable("lastName") String lastName, @PathVariable("phoneNumber") int phoneNumber, 
    @PathVariable("password") String password){
        Manager manager = service.createManager(firstName,lastName, email, phoneNumber, password);
        return manager;
    }

    @PostMapping(value = { "/managers/{email}/{oldPassword}/{newPassword}", "/managers/{email}/{oldPassword}/{newPassword}/" })
    public Manager updateManager( @PathVariable("email") String email, @PathVariable("oldPassword") String oldPassword, 
    @PathVariable("newPassword") String newPassword){
        Manager manager = service.updateManagerPassword(email, oldPassword, newPassword);
        return manager;
    }

    @PostMapping(value = { "/managers/{email}", "/managers/{email}/" })
    public Boolean updateManager(@PathVariable("email") String email){
        Boolean manager = service.deleteManager(email);
        return manager;
    }
}
