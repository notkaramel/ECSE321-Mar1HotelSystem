package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.service.HotelService;
import ca.mcgill.ecse321.Mar1HotelSystem.service.RoomService;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.MultipleRoomDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomRequestDto;

import org.springframework.http.HttpStatus;

/**
 * The controller that handles /room endpoint requests
 * Functionalities:
 * - Get all rooms (GET /rooms)
 * - Get room by ID (GET /rooms/id/{roomId})
 * - Get available rooms (GET /rooms/available)
 * - Get rooms by room type (GET /rooms/roomType/{roomType})
 * - Get rooms by bed type (GET /rooms/bedType/{bedType})
 * - Create a room (POST /createRoom)
 * - Request body: RoomRequestDto schema
 * 
 * @author Lucas Paccico @Lucaspac5
 * @author Antoine Phan @notkaramel
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

    @GetMapping("/room/id/{roomId}")
    public ResponseEntity<RoomResponseDto> getRoomById(@PathVariable("roomId") int roomId) {
        Room room = roomService.getRoomByRoomId(roomId);
        return new ResponseEntity<RoomResponseDto>(new RoomResponseDto(room), HttpStatus.OK);
    }

    @GetMapping("/rooms/available")
    public ResponseEntity<MultipleRoomDto> getAvailableRooms() {
        Iterable<Room> rooms = roomService.getAvailableRooms();
        return new ResponseEntity<MultipleRoomDto>(new MultipleRoomDto(rooms), HttpStatus.OK);
    }

    @GetMapping("/rooms/roomType/{roomType}")
    public ResponseEntity<MultipleRoomDto> getRoomsByRoomType(@PathVariable("roomType") Room.RoomType roomType) {
        Iterable<Room> rooms = roomService.getRoomsByRoomType(roomType);
        return new ResponseEntity<MultipleRoomDto>(new MultipleRoomDto(rooms), HttpStatus.OK);
    }

    @GetMapping("/rooms/bedType/{bedType}")
    public ResponseEntity<MultipleRoomDto> getRoomsByBedType(@PathVariable("bedType") Room.BedType bedType) {
        Iterable<Room> rooms = roomService.getRoomsByBedType(bedType);
        return new ResponseEntity<MultipleRoomDto>(new MultipleRoomDto(rooms), HttpStatus.OK);
    }

    @PostMapping("/room/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoomResponseDto> createRoom(@RequestBody RoomRequestDto roomRequestDto) {
        // Enforcing that there must be a hotel in the system before creating a room
        try {
            hotelService.getHotel();
        } catch (Mar1HotelSystemException e) {
            hotelService.createHotel();
        }
        Room.RoomType roomType = roomRequestDto.getRoomType();
        Room.BedType bedType = roomRequestDto.getBedType();
        boolean isAvailable = roomRequestDto.getIsAvailable();
        int pricePerNight = roomRequestDto.getPricePerNight();
        int maxCapacity = roomRequestDto.getMaxCapacity();

        Room room = roomService.createRoom(roomType, bedType, isAvailable, pricePerNight, maxCapacity);
        return new ResponseEntity<RoomResponseDto>(new RoomResponseDto(room), HttpStatus.CREATED);
    }

    @DeleteMapping("/room/delete/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RoomResponseDto> deleteRoomById(@PathVariable("roomId") int roomId) {
        return new ResponseEntity<>(new RoomResponseDto(roomService.deleteRoomByRoomId(roomId)),
                HttpStatus.valueOf(200));
    }

    @PutMapping("/room/setAvailable/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RoomResponseDto> setRoomAvailable(@PathVariable("roomId") int roomId) {
        return new ResponseEntity<RoomResponseDto>(new RoomResponseDto(roomService.setRoomAvailable(roomId)), HttpStatus.OK);
    }

    @PutMapping("/room/setUnavailable/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RoomResponseDto> setRoomUnavailable(@PathVariable("roomId") int roomId) {
        return new ResponseEntity<RoomResponseDto>(new RoomResponseDto(roomService.setRoomUnavialable(roomId)), HttpStatus.OK);
    }

    @PutMapping("/room/update/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RoomResponseDto> updateRoomByRoomId(@PathVariable("roomId") int roomId,
            @RequestBody RoomRequestDto roomRequestDto) {
        Room room = roomService.updateRoomByRoomId(roomId, roomRequestDto.getRoomType(), roomRequestDto.getBedType(),
                roomRequestDto.getIsAvailable(), roomRequestDto.getPricePerNight(), roomRequestDto.getMaxCapacity());
        return new ResponseEntity<RoomResponseDto>(new RoomResponseDto(room), HttpStatus.OK);
    }
}
