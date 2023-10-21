package ca.mcgill.ecse321.Mar1HotelSystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.service.ManagerService;
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
    
}
