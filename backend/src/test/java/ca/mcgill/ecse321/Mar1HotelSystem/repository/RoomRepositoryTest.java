package ca.mcgill.ecse321.Mar1HotelSystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.*;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * This is the test class for the Room repository.
 *
 * @author Mokhtari, Bilar
 * @author Pacicco, Lucas
 */
@SpringBootTest
public class RoomRepositoryTest {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelScheduleRepository hotelScheduleRepository;

    @Autowired 
    private CustomHoursRepository customHoursRepository;

    @Autowired 
    private OperatingHoursRepository operatingHoursRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @BeforeEach
    @AfterEach
    // Deleting in this particular order from Parent to Child in order to maintain association integrity
    public void clearDatabase() {
        roomRepository.deleteAll();
        hotelRepository.deleteAll();
        hotelScheduleRepository.deleteAll();
        operatingHoursRepository.deleteAll();
        customHoursRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadRoom() {
        //=-=-=-=-=-=-Setting up CustomHours & OperatingHours data-=-=-=-=-=-=//
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
        //=-=-=-=-=-=-Creating hotelSchedule object & Saving hotelSchedule object-=-=-=-=-=-=//
        HotelSchedule hotelSchedule = new HotelSchedule(2023, operatingHoursArray, customHoursArray);
        hotelScheduleRepository.save(hotelSchedule);
        //=-=-=-=-=-=-Creating hotel object & Saving hotel object-=-=-=-=-=-=//
        Hotel hotel = new Hotel(hotelSchedule);
        hotelRepository.save(hotel);
        
        // ------------------
        //Creating room object parameters
        // ------------------
        RoomType roomType = RoomType.Suite;
        BedType bedType = BedType.King;
        boolean isAvailable = true;
        int pricePerNight = 200;
        int maxCapacity = 2;
        
        // ------------------
        // Creating room object
        // ------------------
        Room room = new Room(roomType, bedType, isAvailable, pricePerNight, maxCapacity, hotel);

        //=-=-=-=-=-=- Saving room object -=-=-=-=-=-=//
        roomRepository.save(room);

        //=-=-=-=-=-=- Reading room object -=-=-=-=-=-=//
        int roomId = room.getRoomId();
        room = roomRepository.findRoomByRoomId(roomId);

        // ------------------
        // Assertions
        // ------------------
        assertNotNull(room);
        assertEquals(roomType, room.getRoomType());
        assertEquals(bedType, room.getBedType());
        assertEquals(isAvailable, room.getIsAvailable());
        assertEquals(pricePerNight, room.getPricePerNight());
        assertEquals(maxCapacity, room.getMaxCapacity());
        assertEquals(hotel.getHotelName(), room.getHotel().getHotelName());
    }

}
