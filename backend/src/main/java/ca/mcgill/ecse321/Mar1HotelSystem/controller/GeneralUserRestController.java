package ca.mcgill.ecse321.Mar1HotelSystem.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.dto.GeneralUserDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.GeneralUserDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
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
    public Iterable<GeneralUserDto> getAllGeneralUsers() {
        return StreamSupport.stream(service.getAllGeneralUsers().spliterator(), false)
                    .map(generalUser -> new GeneralUserDto(generalUser.getFirstName(), generalUser.getLastName(), generalUser.getEmail(), (int) generalUser.getPhoneNumber()))
                    .collect(Collectors.toList());
            }
    

    @GetMapping(value = { "/generalUsers/{email}", "/generalUsers/{email}/" })
    public ResponseEntity<GeneralUserDto> getGeneralUser(@PathVariable("email") String email) {
        GeneralUser generalUser = service.getGeneralUser(email);
        GeneralUserDto generalUserBody = new GeneralUserDto(generalUser.getFirstName(), generalUser.getLastName(), generalUser.getEmail(), (int) generalUser.getPhoneNumber());
        return new ResponseEntity<GeneralUserDto>(generalUserBody, HttpStatus.OK);
    }

    @PostMapping(value = { "/generalUsers/{email}/{firstName}/{lastName}/{phoneNumber}/{password}", "/generalUsers/{email}/{firstName}/{lastName}/{phoneNumber}/{password}/" })
    public ResponseEntity<GeneralUserDto> createGeneralUser(@PathVariable("email") String email, @PathVariable("firstName") String firstName, 
    @PathVariable("lastName") String lastName, @PathVariable("phoneNumber") int phoneNumber, 
    @PathVariable("password") String password){
        GeneralUser generalUser = service.createGeneralUser(firstName,lastName, email, phoneNumber);
        GeneralUserDto generalUserBody = new GeneralUserDto(generalUser.getFirstName(), generalUser.getLastName(), generalUser.getEmail(), (int) generalUser.getPhoneNumber());
        return new ResponseEntity<GeneralUserDto>(generalUserBody, HttpStatus.CREATED);
    }

    @PostMapping(value = { "/generalUsers/{oldEmail}/{newEmail}", "/generalUsers/{oldEmail}/{newEmail}?" })
    public ResponseEntity<GeneralUserDto> updateGeneralUser( @PathVariable("oldEmail") String oldEmail, @PathVariable("newEmail") String newEmail){
        GeneralUser generalUser = service.updateGeneralUserEmail(oldEmail, newEmail);
        GeneralUser generalUserDetails = service.getGeneralUser(newEmail);
        GeneralUserDto generalUserBody = new GeneralUserDto(generalUserDetails.getFirstName(), generalUserDetails.getLastName(), generalUserDetails.getEmail(), (int) generalUserDetails.getPhoneNumber());
        return new ResponseEntity<GeneralUserDto>(generalUserBody, HttpStatus.CREATED);
    }

    @PostMapping(value = { "/generalUsers/{email}", "/generalUsers/{email}/" })
    public ResponseEntity<Boolean> deleteGeneralUser(@PathVariable("email") String email){
        Boolean generalUser = service.deleteGeneralUser(email);
        return new ResponseEntity<Boolean>(generalUser, HttpStatus.OK);
    }

}
