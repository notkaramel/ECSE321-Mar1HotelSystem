package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.ManagerDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.MultipleManagerDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;
import ca.mcgill.ecse321.Mar1HotelSystem.service.ManagerService;
import java.util.List;

/**
 * The controller that handles /manager endpoint requests
 * 
 * @author Lucas Paccico @Lucaspac5
 */
@CrossOrigin(origins = "*")
@RestController
public class ManagerRestController {

    @Autowired
    private ManagerService service;

    /*
     * End point to get a list of all managers
     */
    @GetMapping(value = { "/managers", "/managers/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MultipleManagerDto> getAllManagers() {
        List<Manager> managerList = service.getAllManagers();
        MultipleManagerDto managerBody = new MultipleManagerDto(managerList);
        return new ResponseEntity<MultipleManagerDto>(managerBody, HttpStatus.OK);
    }

    /*
     * End point to get a specific manager from email
     */
    @GetMapping(value = { "/managers/{email}", "/managers/{email}/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ManagerDto> getManager(@PathVariable("email") String email) {
        Manager manager = service.getManager(email);
        ManagerDto managerBody = new ManagerDto(manager);
        return new ResponseEntity<ManagerDto>(managerBody, HttpStatus.OK);
    }

    /*
     * End point to create a new manager by post
     */
    @PostMapping(value = { "/managers/create", "/managers/create/" })
    public ResponseEntity<ManagerDto> createManager(@RequestBody ManagerDto managerDto) {
        Manager manager = service.createManager(managerDto.getFirstName(), managerDto.getLastName(),
                managerDto.getEmail(), managerDto.getPhoneNumber(), managerDto.getPassword());
        ManagerDto managerBody = new ManagerDto(manager);
        return new ResponseEntity<ManagerDto>(managerBody, HttpStatus.CREATED);
    }

    /*
     * End point to update a manager password by put
     */
    @PutMapping(value = { "/managers/update/{newPassword}", "/managers/update/{newPassword}/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ManagerDto> updateManager(@RequestBody ManagerDto managerDto,
            @PathVariable("newPassword") String newPassword) {
        Manager manager = service.updateManagerPassword(managerDto.getEmail(), managerDto.getPassword(), newPassword);
        managerDto.setPassword(newPassword);
        return new ResponseEntity<ManagerDto>(managerDto, HttpStatus.OK);
    }

    /*
     * End point to delete a specific manager by post
     */
    @PostMapping(value = { "/managers/delete", "/managers/delete/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteManager(@RequestBody ManagerDto managerDto) {
        Boolean manager = service.deleteManager(managerDto.getEmail());
        return new ResponseEntity<Boolean>(manager, HttpStatus.OK);
    }
}
