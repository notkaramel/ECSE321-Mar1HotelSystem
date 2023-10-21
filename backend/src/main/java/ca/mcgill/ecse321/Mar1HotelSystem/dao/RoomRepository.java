package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import org.springframework.data.repository.CrudRepository;

/**
 * The CRUD Repository Interface to store and retrieve all Room objects.
 * 
 * @author Antoine Phan (@notkaramel)
 */
public interface RoomRepository extends CrudRepository<Room, Integer> {
    /**
     * Find a Room object by its roomId.
     * 
     * @param roomId
     * @return the corresponding Room object
     */
    public Room findRoomByRoomId(int roomId);

    /**
     * Find a Room object by roomType attribute.
     * 
     * @param roomType
     * @return the corresponding Room object
     */
    public Room findRoomByRoomType(Room.RoomType roomType);
}
