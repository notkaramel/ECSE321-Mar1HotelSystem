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
     * Find a list of Rooms by roomType attribute.
     * 
     * @param roomType
     * @return the corresponding Room object
     */
    public Iterable<Room> findRoomsByRoomType(Room.RoomType roomType);

    /**
     * Find a list of Rooms that are available
     * 
     * @param availability
     * @return an Iterable list of Room 
     */
    public Iterable<Room> findRoomsByIsAvailable(boolean isAvailable);
}
