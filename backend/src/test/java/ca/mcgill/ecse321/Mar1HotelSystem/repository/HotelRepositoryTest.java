package ca.mcgill.ecse321.Mar1HotelSystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.*;

import jakarta.transaction.Transactional;

/**
 * This test class is for the BookingRepository CRUD against the database.
 * 
 * @author Bilar Mokhtari (@bmokhtari)
 * @author Antoine Phan (@notkaramel)
 */
@SpringBootTest
public class HotelRepositoryTest {

    @Autowired 
    private CustomHoursRepository customHoursRepository;

    @Autowired 
    private OperatingHoursRepository operatingHoursRepository;

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
        hotelScheduleRepository.deleteAll();
        operatingHoursRepository.deleteAll();
        customHoursRepository.deleteAll();
    }

    // ------------------
    // Test Methods
    // ------------------

    @Test
    @Transactional
    public void testPersistAndLoadHotel() {

        // =-=-=-=-=-=- Create HotelSchedule object -=-=-=-=-=-=//
        Date date = new Date();
        CustomHours customHours = new CustomHours(date, 8, 20);
        OperatingHours operatingHours = new OperatingHours(DayOfWeek.Monday, 8, 20);
        CustomHours[] customHoursArray = new CustomHours[1];
        OperatingHours[] operatingHoursArray = new OperatingHours[1];
        customHoursRepository.save(customHours);
        customHours = customHoursRepository.findCustomHoursByDate(date);
        operatingHoursRepository.save(operatingHours);
        operatingHours = operatingHoursRepository.findOperatingHoursByOpeningHour(8);

        customHoursArray[0] = customHours;
        operatingHoursArray[0] = operatingHours;
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
        roomRepository.save(room);
        int roomId = room.getRoomId();
        room = roomRepository.findRoomByRoomId(roomId);
        // Add the saved Room to the saved Hotel
        hotel.addRoom(room);

        // Retrieve saved Hotel
        hotel = hotelRepository.findHotelByHotelSchedule(hotelSchedule);

        // ------------------
        // Assertions
        // ------------------
        assertNotNull(hotel);
        assertEquals(hotelSchedule.getYear(), hotel.getHotelSchedule().getYear());
        assertTrue(hotel.hasRooms());

    }
}
