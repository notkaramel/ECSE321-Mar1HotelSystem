package ca.mcgill.ecse321.Mar1HotelSystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.service.HotelService;
import ca.mcgill.ecse321.Mar1HotelSystem.service.RoomService;
import jakarta.validation.Valid;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomResponseDto;
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

    @Autowired
    private HotelService hotelService;

    @GetMapping("/rooms")
    public ResponseEntity<MultipleRoomDto> getAllRooms() {
        Iterable<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<MultipleRoomDto>(new MultipleRoomDto(rooms), HttpStatus.OK);
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<RoomResponseDto> getRoomById(@PathVariable @Valid int roomId) {
        Room room = roomService.getRoomByRoomId(roomId);
        return new ResponseEntity<RoomResponseDto>(new RoomResponseDto(room), HttpStatus.OK);
    }

    @GetMapping("/rooms/available")
    public ResponseEntity<MultipleRoomDto> getAvailableRooms() {
        Iterable<Room> rooms = roomService.getAvailableRooms();
        return new ResponseEntity<MultipleRoomDto>(new MultipleRoomDto(rooms), HttpStatus.OK);
    }

    @GetMapping("/rooms/{roomType}")
    public ResponseEntity<MultipleRoomDto> getRoomsByRoomType(@PathVariable @Valid Room.RoomType roomType) {
        Iterable<Room> rooms = roomService.getRoomsByRoomType(roomType);
        return new ResponseEntity<MultipleRoomDto>(new MultipleRoomDto(rooms), HttpStatus.OK);
    }

    @GetMapping("/rooms/{bedType}")
    public ResponseEntity<MultipleRoomDto> getRoomsByBedType(@PathVariable @Valid Room.BedType bedType) {
        Iterable<Room> rooms = roomService.getRoomsByBedType(bedType);
        return new ResponseEntity<MultipleRoomDto>(new MultipleRoomDto(rooms), HttpStatus.OK);
    }

    @PostMapping("/createRoom")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoomResponseDto> createRoom(Room.RoomType roomType, Room.BedType bedType, boolean isAvailable, int pricePerNight, int maxCapacity) {
        // Enforcing that there must be a hotel in the system before creating a room
        Hotel hotel = hotelService.getHotel();
        if (hotel == null) {
            hotelService.createHotel();
        }
        Room room = roomService.createRoom(roomType, bedType, isAvailable, pricePerNight, maxCapacity);
        return new ResponseEntity<RoomResponseDto>(new RoomResponseDto(room), HttpStatus.CREATED);
    }
}
