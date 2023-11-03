package ca.mcgill.ecse321.Mar1HotelSystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
	private RoomService roomService;

    @GetMapping("/rooms")
    public ResponseEntity<MultipleRoomDto> getAllRooms() {
        Iterable<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<MultipleRoomDto>(new MultipleRoomDto(rooms), HttpStatus.OK);
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(int roomId) {
        Room room = roomService.getRoomByRoomId(roomId);
        return new ResponseEntity<RoomDto>(new RoomDto(room), HttpStatus.OK);
    }

    @PostMapping("/rooms/{roomId}")
    public ResponseEntity<RoomDto> createRoom(Room.RoomType roomType, Room.BedType bedType, boolean isAvailable, int pricePerNight, int maxCapacity) {
        Room room = roomService.createRoom(roomType, bedType, isAvailable, pricePerNight, maxCapacity);
        return new ResponseEntity<RoomDto>(new RoomDto(room), HttpStatus.OK);
    }
}
