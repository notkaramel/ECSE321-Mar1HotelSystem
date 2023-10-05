package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Integer> {
    Room findRoomByRoomNumber(int roomNumber);
}
