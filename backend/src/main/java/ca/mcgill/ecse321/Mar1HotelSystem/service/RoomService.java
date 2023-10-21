package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;
import jakarta.transaction.Transactional;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Transactional
    public Room createRoom(RoomType roomType, BedType bedType, boolean isAvailable, int pricePerNight, int maxCapacity,
            Hotel hotel) {
        Room room = new Room();
        room.setRoomType(roomType);
        room.setBedType(bedType);
        room.setIsAvailable(isAvailable);
        room.setPricePerNight(pricePerNight);
        room.setMaxCapacity(maxCapacity);
        room.setHotel(hotel);
        roomRepository.save(room);
        return room;
    }

    @Transactional
    public Room getRoomById(int roomId) {
        Room room = roomRepository.findRoomByRoomId(roomId);
        return room;
    }

    @Transactional
    public List<Room> getRoomsByRoomType(RoomType roomType) {
        return ServiceUtils.toList(roomRepository.findRoomsByRoomType(roomType));
    }

    @Transactional
    public boolean deleteRoom(int roomId) {
        Room room = roomRepository.findRoomByRoomId(roomId);
        try {
            roomRepository.delete(room);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public List<Room> getAllRooms() {
        return ServiceUtils.toList(roomRepository.findAll());
    }
}
