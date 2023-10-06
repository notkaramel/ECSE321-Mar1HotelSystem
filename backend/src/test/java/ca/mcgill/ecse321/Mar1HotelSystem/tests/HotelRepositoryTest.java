package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelScheduleRepository;

import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;

/**
 * This test is for the booking class
 * 
 * @author Mokhtari, Bilar
 * 
 */
@SpringBootTest
public class HotelRepositoryTest {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelScheduleRepository hotelScheduleRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Clears the database before and after each test
    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        roomRepository.deleteAll();
        hotelRepository.deleteAll();
    }

    // ------------------
    // Test Methods
    // ------------------

    @Test
    public void testPersistAndLoadHotel() {

        // =-=-=-=-=-=- Create HotelSchedule object -=-=-=-=-=-=//

        Date date = new Date();
        CustomHours customHours1 = new CustomHours(date, 8, 20);
        CustomHours customHours2 = new CustomHours(date, 8, 20);
        OperatingHours operatingHours = new OperatingHours(DayOfWeek.Monday, 8, 20);

        CustomHours[] customHoursArray = { customHours1, customHours2 };
        OperatingHours[] operatingHoursArray = { operatingHours };

        HotelSchedule hotelSchedule = new HotelSchedule(2023, operatingHoursArray, customHoursArray);
        // --------------------------------//

        // ------------------
        // Create and Save Hotel
        // ------------------
        Hotel hotel = new Hotel(hotelSchedule);
        hotelScheduleRepository.save(hotelSchedule);
        hotelRepository.save(hotel);
        hotel = hotelRepository.findHotelByHotelSchedule(hotelSchedule);

        // Create Room parameters
        RoomType roomType = RoomType.Suite;
        BedType bedType = BedType.King;
        boolean isAvailable = true;
        int pricePerNight = 200;
        int maxCapacity = 2; 

        // ------------------
        // Create and Save Room
        // ------------------
        Room room = new Room(roomType, bedType, isAvailable, pricePerNight, maxCapacity, hotel);
        int roomId = room.getRoomId(); 
        roomRepository.save(room);
        room = roomRepository.findRoomByRoomId(roomId);
        // Add the saved Room to the saved Hotel
        hotel.addRoom(room);

        // Retrieve saved Hotel
        hotel = hotelRepository.findHotelByHotelSchedule(hotelSchedule);

        // ------------------
        // Assertions
        // ------------------
        assertNotNull(hotel);
        assertEquals(hotelSchedule, hotel.getHotelSchedule());
        assertTrue(hotel.hasRooms());
        assertEquals(room, hotel.getRoom(roomId));

    }
}
