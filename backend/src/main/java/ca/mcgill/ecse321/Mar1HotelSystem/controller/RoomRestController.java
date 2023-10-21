package ca.mcgill.ecse321.Mar1HotelSystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.service.RoomService;
/**
 * The controller that handles /room endpoint requests
 * Required functionalities:
 * - Get all rooms (GET)
 * - Return/Get room by ID (GET)
 * 
 * @author Lucas Paccico @Lucaspac5
 */
@CrossOrigin(origins = "*")
@RestController
public class RoomRestController {
    
    @Autowired
	private RoomService service;

}
