package ca.mcgill.ecse321.Mar1HotelSystem.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.service.GeneralUserService;
/**
 * The controller that handles /guest endpoint requests
 * Required functionalities:
 * - Display guest's booking (GET)
 * - etc.
 * DTOs might used:
 * - CustomerDto
 * - BookingDto
 * - RoomDto
 * - etc. * @author Lucas Paccico @Lucaspac5
 */
@CrossOrigin(origins = "*")
@RestController
public class GeneralUserRestController {
    
    @Autowired
	private GeneralUserService service;

     @GetMapping(value = { "/generalUsers", "/generalUsers/" })
    public List<GeneralUser> getAllGeneralUsers() {
        List<GeneralUser> generalUsers = new ArrayList<>();
        generalUsers = service.getAllGeneralUsers();
        return generalUsers;
    }

    @GetMapping(value = { "/generalUsers/{email}", "/generalUsers/{email}/" })
    public GeneralUser getGeneralUser(@PathVariable("email") String email) {
        GeneralUser generalUser = service.getGeneralUser(email);
        return generalUser;
    }

    @PostMapping(value = { "/generalUsers/{email}/{firstName}/{lastName}/{phoneNumber}", "/generalUsers/{email}/{firstName}/{lastName}/{phoneNumber}/" })
    public GeneralUser createGeneralUser( @PathVariable("email") String email, @PathVariable("firstName") String firstName, 
    @PathVariable("lastName") String lastName, @PathVariable("phoneNumber") int phoneNumber){
        GeneralUser generalUser = service.createGeneralUser(firstName,lastName, email, phoneNumber);
        return generalUser;
    }

    @PostMapping(value = { "/generalUsers/{oldEmail}/{newEmail}", "/generalUsers/{oldEmail}/{newEmail}/" })
    public GeneralUser updateGeneralUser( @PathVariable("oldEmail") String oldEmail, @PathVariable("newEmail") String newEmail){
        GeneralUser generalUser = service.updateGeneralUserEmail(oldEmail, newEmail);
        return generalUser;
    }

    @PostMapping(value = { "/generalUsers/{email}", "/generalUsers/{email}/" })
    public Boolean updateGeneralUser(@PathVariable("email") String email){
        Boolean generalUser = service.deleteGeneralUser(email);
        return generalUser;
    }

}
