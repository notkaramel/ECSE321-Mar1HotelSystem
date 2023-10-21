package ca.mcgill.ecse321.Mar1HotelSystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.service.GeneralUserService;
/**
 * The controller that handles /guest endpoint requests
 * 
 * @author Lucas Paccico @Lucaspac5
 */
@CrossOrigin(origins = "*")
@RestController
public class GeneralUserRestController {
    
    @Autowired
	private GeneralUserService service;

}
