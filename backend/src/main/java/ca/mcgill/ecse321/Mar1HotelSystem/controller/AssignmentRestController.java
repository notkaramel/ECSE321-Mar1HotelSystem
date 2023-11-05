package ca.mcgill.ecse321.Mar1HotelSystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.service.AssignmentService;
/**
 * The controller that handles /assignment endpoint requests
 * Required functionalities:
 * - Display/Return all assignments (GET)
 * - Assign an employee to a room (Receive POST request)
 * - etc.
 * 
 * @author Lucas Paccico @Lucaspac5
 */
@CrossOrigin(origins = "*")
@RestController
public class AssignmentRestController {
    
    @Autowired
	private AssignmentService service;

}