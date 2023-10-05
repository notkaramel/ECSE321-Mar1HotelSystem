package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.repository.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.repository.HotelRepository;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomRepositoryTest {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        roomRepository.deleteAll();
        hotelRepository.deleteAll();
    }
    @Test
    public void testPersistAndLoadRoom() {
        //=-=-=-=-=-=- Create object -=-=-=-=-=-=//
        Hotel hotel = new Hotel();
        hotel = hotelRepository.save(hotel);
        //=-=-=-=-=-=- Create object -=-=-=-=-=-=//
        RoomType roomType = RoomType.Suite;
        BedType bedType = BedType.King;
        boolean isAvailable = true;
        int pricePerNight = 200;
        int maxCapacity = 2;
        int id = 1
        //=-=-=-=-=-=- Create object -=-=-=-=-=-=//
        Room room = new Room(roomType, bedType, isAvailable, pricePerNight, maxCapacity, hotel, id);
        //=-=-=-=-=-=- Save object -=-=-=-=-=-=//
        room = roomRepository.save(room);
        //=-=-=-=-=-=- Read object -=-=-=-=-=-=//
        int id = room.id;
        //=-=-=-=-=-=- Assertions-=-=-=-=-=-=//
        assertNotNull(id);
        assertEquals(roomType, room.getRoomType());
        assertEquals(bedType, room.getBedType());
        assertEquals(isAvailable, room.getIsAvailable());
        assertEquals(pricePerNight, room.getPricePerNight());
        assertEquals(maxCapacity, room.getMaxCapacity());
        assertEquals(hotel, room.getHotel());
    }
    
}
