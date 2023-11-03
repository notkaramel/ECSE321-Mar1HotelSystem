package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * The controller that handles / endpoint requests (root/index page)
 * DTOs might used:
 * - HotelDto
 * - HotelScheduleDto
 * - CustomHoursDto
 * - OperatingHoursDto
 * @author Lucas Paccico @Lucaspac5
 */
@CrossOrigin(origins = "*")
@RestController
public class Mar1HotelSystemRestController {

    @GetMapping("/")
    public ResponseEntity<String> homepage() {
        return new ResponseEntity<String>("Welcome to the Mar-1 Hotel System!\n", HttpStatus.OK);
    }

    @GetMapping("/coffee")
    public ResponseEntity<String> brewCoffee() {
        return new ResponseEntity<String>("I am a teapot 🫖\n", HttpStatus.I_AM_A_TEAPOT);
    }
}