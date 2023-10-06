package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.Mar1HotelSystem.model.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.*;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelScheduleRepository;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomRepositoryTest {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelScheduleRepository hotelScheduleRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        roomRepository.deleteAll();
        hotelRepository.deleteAll();
        hotelScheduleRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadRoom() {
        //=-=-=-=-=-=- Create object -=-=-=-=-=-=//
        Date date = new Date();
        CustomHours customHours = new CustomHours(date, 8, 20);
        OperatingHours operatingHours = new OperatingHours(DayOfWeek.Monday, 8, 20);
        CustomHours[] customHoursArray = new CustomHours[1];
        OperatingHours[] operatingHoursArray = new OperatingHours[1];
        customHoursArray[0] = customHours;
        operatingHoursArray[0] = operatingHours;
        HotelSchedule hotelSchedule = new HotelSchedule(2023, operatingHoursArray, customHoursArray);
        hotelScheduleRepository.save(hotelSchedule);
        Hotel hotel = new Hotel(hotelSchedule);
        hotelRepository.save(hotel);
        
        //=-=-=-=-=-=- Create object -=-=-=-=-=-=//
        RoomType roomType = RoomType.Suite;
        BedType bedType = BedType.King;
        boolean isAvailable = true;
        int pricePerNight = 200;
        int maxCapacity = 2;
        
        //=-=-=-=-=-=- Create object -=-=-=-=-=-=//
        Room room = new Room(roomType, bedType, isAvailable, pricePerNight, maxCapacity, hotel);
        //=-=-=-=-=-=- Save object -=-=-=-=-=-=//
        roomRepository.save(room);
        //=-=-=-=-=-=- Read object -=-=-=-=-=-=//
        int roomId = room.getRoomId();
        room = roomRepository.findRoomByRoomId(roomId);
        //=-=-=-=-=-=- Assertions-=-=-=-=-=-=//
        assertNotNull(room);
        assertEquals(roomType, room.getRoomType());
        assertEquals(bedType, room.getBedType());
        assertEquals(isAvailable, room.getIsAvailable());
        assertEquals(pricePerNight, room.getPricePerNight());
        assertEquals(maxCapacity, room.getMaxCapacity());
        assertEquals(hotel.getHotelName(), room.getHotel().getHotelName());
    }

}
