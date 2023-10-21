package ca.mcgill.ecse321.Mar1HotelSystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.service.PaymentService;
/**
 * The controller that handles /payment endpoint requests
 * Required functionalities:
 * - Recieve payment request (POST)
 * - Return payment result as DTO
 * DTOs might used:
 * - PaymentDTO
 * 
 * @author Lucas Paccico @Lucaspac5
 */
@CrossOrigin(origins = "*")
@RestController
public class PaymentRestController {
    
    @Autowired
	private PaymentService service;

}
