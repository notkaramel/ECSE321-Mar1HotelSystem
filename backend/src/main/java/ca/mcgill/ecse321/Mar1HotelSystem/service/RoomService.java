package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import jakarta.transaction.Transactional;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Transactional
    public List<Room> getAllRooms() {
        return ServiceUtils.toList(roomRepository.findAll());
    }
}
