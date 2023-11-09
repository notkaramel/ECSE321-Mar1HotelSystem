package ca.mcgill.ecse321.Mar1HotelSystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.service.EmployeeService;
/**
 * The controller that handles /employee endpoint requests
 * Required functionality:
 * - Create an employee
 * - Return an employee (as DTO)
 * - Return all employees (as DTOs)
 * - Display their schedule (as DTO)
 * - etc.
 * DTOs might used:
 * - EmployeeDto
 * - ShiftDto
 * 
 * @author Lucas Paccico @Lucaspac5
 */
@CrossOrigin(origins = "*")
@RestController
public class EmployeeRestController {
    @Autowired
	private EmployeeService service;


}
