package ca.mcgill.ecse321.Mar1HotelSystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.service.RequestService;

@CrossOrigin(origins = "*")
@RestController
public class RequestRestController {
    
    @Autowired
	private RequestService service;

}
