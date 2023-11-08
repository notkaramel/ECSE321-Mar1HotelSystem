package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.GeneralUserDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.ManagerDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;
import ca.mcgill.ecse321.Mar1HotelSystem.service.ManagerService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
    public Iterable<ManagerDto> getAllManagers() {
        return StreamSupport.stream(service.getAllManagers().spliterator(), false)
                    .map(manager -> new ManagerDto(manager.getFirstName(), manager.getLastName(), manager.getEmail(), (int) manager.getPhoneNumber(), manager.getPassword()))
                    .collect(Collectors.toList());
            }
    

    @GetMapping(value = { "/managers/{email}", "/managers/{email}/" })
    public ResponseEntity<ManagerDto> getManager(@PathVariable("email") String email) {
        Manager manager = service.getManager(email);
        ManagerDto managerBody = new ManagerDto(manager.getFirstName(), manager.getLastName(), manager.getEmail(), (int) manager.getPhoneNumber(), manager.getPassword());
        return new ResponseEntity<ManagerDto>(managerBody, HttpStatus.OK);
    }

    @PostMapping(value = { "/managers/{email}/{firstName}/{lastName}/{phoneNumber}/{password}", "/managers/{email}/{firstName}/{lastName}/{phoneNumber}/{password}/" })
    public ResponseEntity<ManagerDto> createManager(@PathVariable("email") String email, @PathVariable("firstName") String firstName, 
    @PathVariable("lastName") String lastName, @PathVariable("phoneNumber") int phoneNumber, 
    @PathVariable("password") String password){
        Manager manager = service.createManager(firstName,lastName, email, phoneNumber, password);
        ManagerDto managerBody = new ManagerDto(manager.getFirstName(), manager.getLastName(), manager.getEmail(), (int) manager.getPhoneNumber(), manager.getPassword());
        return new ResponseEntity<ManagerDto>(managerBody, HttpStatus.CREATED);
    }

    @PostMapping(value = { "/managers/{email}/{oldPassword}/{newPassword}", "/managers/{email}/{oldPassword}/{newPassword}/" })
    public ResponseEntity<ManagerDto> updateManager( @PathVariable("email") String email, @PathVariable("oldPassword") String oldPassword, 
    @PathVariable("newPassword") String newPassword){
        Manager manager = service.updateManagerPassword(email, oldPassword, newPassword);
        Manager managerDetails = service.getManager(email);
        ManagerDto managerBody = new ManagerDto(managerDetails.getFirstName(), managerDetails.getLastName(), managerDetails.getEmail(), (int) managerDetails.getPhoneNumber(), managerDetails.getPassword());
        return new ResponseEntity<ManagerDto>(managerBody, HttpStatus.OK);
    }

    @PostMapping(value = { "/managers/{email}", "/managers/{email}/" })
    public ResponseEntity<Boolean> deleteManager(@PathVariable("email") String email){
        Boolean manager = service.deleteManager(email);
        return new ResponseEntity<Boolean>(manager, HttpStatus.OK);
    }
}
