package ca.mcgill.ecse321.Mar1HotelSystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import ca.mcgill.ecse321.Mar1HotelSystem.service.RoomService;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.MultipleRoomDto;
import org.springframework.http.HttpStatus;

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

    @GetMapping(value = { "/rooms", "/rooms/" })
    public ResponseEntity<MultipleRoomDto> getAllRooms() {
        Iterable<Room> rooms = service.getAllRooms();
        return new ResponseEntity<MultipleRoomDto>(new MultipleRoomDto(rooms), HttpStatus.OK);
    }


}
