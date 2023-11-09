package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.ManagerDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;
import ca.mcgill.ecse321.Mar1HotelSystem.service.ManagerService;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    @ResponseStatus(HttpStatus.OK)
    public Iterable<ManagerDto> getAllManagers() {
        return StreamSupport.stream(service.getAllManagers().spliterator(), false)
                .map(manager -> new ManagerDto(manager))
                .collect(Collectors.toList());
    }

    @GetMapping(value = { "/managers/{email}", "/managers/{email}/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ManagerDto> getManager(@PathVariable("email") String email) {
        Manager manager = service.getManager(email);
        ManagerDto managerBody = new ManagerDto(manager);
        return new ResponseEntity<ManagerDto>(managerBody, HttpStatus.OK);
    }

    @PostMapping(value = { "/managers/create", "/managers/create/" })
    public ResponseEntity<ManagerDto> createManager(@RequestBody ManagerDto managerDto) {
        Manager manager = service.createManager(managerDto.getFirstName(), managerDto.getLastName(),
                managerDto.getEmail(), managerDto.getPhoneNumber(), managerDto.getPassword());
        ManagerDto managerBody = new ManagerDto(manager);
        return new ResponseEntity<ManagerDto>(managerBody, HttpStatus.CREATED);
    }

    @PostMapping(value = { "/managers/{newPassword}", "/managers/{newPassword}/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ManagerDto> updateManager(@RequestBody ManagerDto managerDto,
            @PathVariable("newPassword") String newPassword) {
        Manager manager = service.updateManagerPassword(managerDto.getEmail(), managerDto.getPassword(), newPassword);
        Manager managerDetails = service.getManager(managerDto.getEmail());
        ManagerDto managerBody = new ManagerDto(managerDetails);
        return new ResponseEntity<ManagerDto>(managerBody, HttpStatus.OK);
    }

    @PostMapping(value = { "/managers/delete", "/managers/delete/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteManager(@RequestBody ManagerDto managerDto) {
        Boolean manager = service.deleteManager(managerDto.getEmail());
        return new ResponseEntity<Boolean>(manager, HttpStatus.OK);
    }
}
