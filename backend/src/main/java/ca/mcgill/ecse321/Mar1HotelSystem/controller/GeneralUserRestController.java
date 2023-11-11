package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.GeneralUserDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.MultipleGeneralUserDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.service.GeneralUserService;

/**
 * The controller that handles /general user endpoint requests
 * @author Lucas Paccico @Lucaspac5
 */
@CrossOrigin(origins = "*")
@RestController
public class GeneralUserRestController {

    @Autowired
    private GeneralUserService service;

    /*
     * End point to get a list of general users
     */
    @GetMapping(value = { "/generalUsers", "/generalUsers/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MultipleGeneralUserDto> getAllGeneralUsers() {
        List<GeneralUser> generalUserList = service.getAllGeneralUsers();
        MultipleGeneralUserDto generalUserBody = new MultipleGeneralUserDto(generalUserList);
        return new ResponseEntity<MultipleGeneralUserDto>(generalUserBody, HttpStatus.OK);
    }

    /*
     * End point to get a specific general user by email
     */
    @GetMapping(value = { "/generalUsers/{email}", "/generalUsers/{email}/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GeneralUserDto> getGeneralUser(@PathVariable("email") String email) {
        GeneralUser generalUser = service.getGeneralUser(email);
        GeneralUserDto generalUserBody = new GeneralUserDto(generalUser);
        return new ResponseEntity<GeneralUserDto>(generalUserBody, HttpStatus.OK);
    }

    /*
     * End point to create a new general user by post
     */
    @PostMapping(value = { "/generalUsers/create", "/generalUsers/create/" })
    public ResponseEntity<GeneralUserDto> createGeneralUser(@RequestBody GeneralUserDto generalUserDto) {
        GeneralUser generalUser = service.createGeneralUser(generalUserDto.getFirstName(), generalUserDto.getLastName(),
                generalUserDto.getEmail(), generalUserDto.getPhoneNumber());
        GeneralUserDto generalUserBody = new GeneralUserDto(generalUser);
        return new ResponseEntity<GeneralUserDto>(generalUserBody, HttpStatus.CREATED);
    }

    /*
     * End point to delete a specific general user
     */
    @PostMapping(value = { "/generalUsers/delete", "/generalUsers/delete/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteGeneralUser(@RequestBody GeneralUserDto generalUserDto) {
        Boolean generalUser = service.deleteGeneralUser(generalUserDto.getEmail());
        return new ResponseEntity<Boolean>(generalUser, HttpStatus.OK);
    }

}
