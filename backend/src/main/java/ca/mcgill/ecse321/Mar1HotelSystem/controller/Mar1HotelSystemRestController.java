package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class Mar1HotelSystemRestController {

	@Autowired
	private Mar1HotelSystemService service;

}