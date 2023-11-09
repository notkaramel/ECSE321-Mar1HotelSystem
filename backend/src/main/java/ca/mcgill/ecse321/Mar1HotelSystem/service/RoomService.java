package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;
import jakarta.transaction.Transactional;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Transactional
    public Room createRoom(RoomType roomType, BedType bedType, boolean isAvailable, int pricePerNight,
            int maxCapacity) {
        Hotel hotel = hotelRepository.findHotelByHotelName("Mar-1 Hotel");
        if (hotel == null) {
            hotel = new Hotel();
            hotelRepository.save(hotel);
        }
        Room room = new Room(roomType, bedType, isAvailable, pricePerNight, maxCapacity, hotel);
        roomRepository.save(room);
        return room;
    }

    @Transactional
    public Room getRoomByRoomId(int roomId) {
        Room room = roomRepository.findRoomByRoomId(roomId);
        if (room == null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Can't find room with id {" + roomId + "}");
        }
        return room;
    }

    /*
     * Return a list of rooms that have the same Room type.
     * By default, SpringBoot will convert the enum to a string.
     * If request has invalid enum RoomType, SpringBoot will throw an exception
     * with code 400 - Bad Request. So, no need for Mar1HotelSystemException.
     */
    @Transactional
    public List<Room> getRoomsByRoomType(RoomType roomType) {
        return ServiceUtils.toList(roomRepository.findRoomsByRoomType(roomType));
    }

    @Transactional
    public List<Room> getAllRooms() {
        return ServiceUtils.toList(roomRepository.findAll());
    }

    @Transactional
    public List<Room> getAvailableRooms() {
        return ServiceUtils.toList(roomRepository.findRoomsByIsAvailable(true));
    }

    @Transactional
    public List<Room> getUnavailableRooms() {
        return ServiceUtils.toList(roomRepository.findRoomsByIsAvailable(false));
    }

    /*
     * Return a list of rooms that have the same bed type.
     * By default, SpringBoot will convert the enum to a string.
     * If request has invalid enum BedType, SpringBoot will throw an exception
     * with code 400 - Bad Request. So, no need for Mar1HotelSystemException.
     */
    @Transactional
    public List<Room> getRoomsByBedType(BedType bedType) {
        return ServiceUtils.toList(roomRepository.findRoomsByBedType(bedType));
    }

    @Transactional
    public Room setRoomUnavialable(int roomId) {
        try {
            Room room = roomRepository.findRoomByRoomId(roomId);
            room.setIsAvailable(false);
            return roomRepository.save(room);
        } catch (Exception e) {
            String exceptionMessage = "Unable to set room with id {" + roomId + "} to unavailable";
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, exceptionMessage);
        }
    }

    @Transactional
    public Room setRoomAvailable(int roomId) {
        try {
            Room room = roomRepository.findRoomByRoomId(roomId);
            room.setIsAvailable(true);
            return roomRepository.save(room);
        } catch (Exception e) {
            String exceptionMessage = "Unable to set room with id {" + roomId + "} to available";
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, exceptionMessage);
        }
    }

    @Transactional
    public Room deleteRoomByRoomId(int roomId) {
        try {
            Room room = roomRepository.findRoomByRoomId(roomId);
            roomRepository.delete(room);
            return room;
        } catch (Exception e) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Can't delete room with id {" + roomId + "}");
        }
    }

    @Transactional
    public Room updateRoomByRoomId(int roomId, RoomType roomType, BedType bedType, boolean isAvailable,
            int pricePerNight, int maxCapacity) {
        try {
            Room room = roomRepository.findRoomByRoomId(roomId);
            room.setRoomType(roomType);
            room.setBedType(bedType);
            room.setIsAvailable(isAvailable);
            room.setPricePerNight(pricePerNight);
            room.setMaxCapacity(maxCapacity);
            roomRepository.save(room);
            return room;
        } catch (Exception e) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Can't update room with id {" + roomId + "}");
        }
    }
}
