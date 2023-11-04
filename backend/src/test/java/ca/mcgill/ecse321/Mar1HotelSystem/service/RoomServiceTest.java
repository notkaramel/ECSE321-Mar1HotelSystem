package ca.mcgill.ecse321.Mar1HotelSystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.lenient;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
    @InjectMocks
    private RoomService roomService;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private RoomRepository roomRepository;

    /**
     * Set mock behaviour for Repository methods
     */ 
    @BeforeEach
    public void setMockOutput() {
        Hotel hotel = new Hotel();
        lenient().when(hotelRepository.findHotelByHotelName(isA(String.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals("Mar-1 Hotel")) {
                return hotel;
            } else {
                return null;
            }
        });

        Room roomSuite1 = new Room(RoomType.Suite, BedType.King, true, 400, 8, hotel);
        Room roomSuite2 = new Room(RoomType.Suite, BedType.King, true, 300, 6, hotel);
        roomSuite1.setRoomId(1);
        roomSuite2.setRoomId(2);
        
        Room roomDeluxe3 = new Room(RoomType.Deluxe, BedType.Queen, true, 200, 3, hotel);
        Room roomDeluxe4 = new Room(RoomType.Deluxe, BedType.Queen, true, 100, 2, hotel);
        roomDeluxe3.setRoomId(3);
        roomDeluxe4.setRoomId(4);

        Room roomRegular5 = new Room(RoomType.Regular, BedType.Queen, true, 50, 2, hotel);
        Room roomRegular6 = new Room(RoomType.Regular, BedType.Doubles, false, 10, 2, hotel);
        roomRegular5.setRoomId(5);
        roomRegular6.setRoomId(6);

        lenient().when(roomRepository.findRoomsByRoomType(isA(Room.RoomType.class))).thenAnswer((InvocationOnMock invocation) -> {
            ArrayList<Room> rooms = new ArrayList<Room>();
            if (invocation.getArgument(0).equals(RoomType.Deluxe)) {
                rooms.add(roomDeluxe3);
                rooms.add(roomDeluxe4);
                return rooms;
            } else if (invocation.getArgument(0).equals(RoomType.Deluxe)) {
                rooms.add(roomSuite1);
                rooms.add(roomSuite2);
                return rooms;
            } else if (invocation.getArgument(0).equals(RoomType.Regular)) {
                rooms.add(roomRegular5);
                rooms.add(roomRegular6);
                return rooms;
            } else {
                return null;
            }
        });

        lenient().when(roomRepository.findRoomByRoomId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            int roomId = invocation.getArgument(0);
            return switch (roomId) {
                case 1 -> roomSuite1;
                case 2 -> roomSuite2;
                case 3 -> roomDeluxe3;
                case 4 -> roomDeluxe4;
                case 5 -> roomRegular5;
                case 6 -> roomRegular6;
                default -> null;
            };
        });

        lenient().when(roomRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            ArrayList<Room> rooms = new ArrayList<Room>();
            rooms.add(roomSuite1);
            rooms.add(roomSuite2);
            rooms.add(roomDeluxe3);
            rooms.add(roomDeluxe4);
            rooms.add(roomRegular5);
            rooms.add(roomRegular6);
            return rooms;
        });
    }


    @Test
    public void testGetAllRooms() {
        ArrayList<Room> rooms = new ArrayList<Room>();
        try {
            rooms = (ArrayList<Room>) roomService.getAllRooms();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(6, rooms.size());
    }

    @Test
    public void testGetAllRoomsByRoomType() {
        ArrayList<Room> rooms = new ArrayList<Room>();
        try {
            rooms = (ArrayList<Room>) roomService.getRoomsByRoomType(RoomType.Deluxe);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(2, rooms.size());
    }

    @Test
    public void testGetRoomByRoomId() {
        Room room = new Room();
        try {
            room = roomService.getRoomByRoomId(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(1, room.getRoomId());
    }

    @Test
    public void testCreateRoom() {
        Room room = new Room();
        try {
            room = roomService.createRoom(RoomType.Deluxe, Room.BedType.Queen, true, 100, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(RoomType.Deluxe, room.getRoomType());
        assertEquals(Room.BedType.Queen, room.getBedType());
        assertEquals(true, room.getIsAvailable());
        assertEquals(100, room.getPricePerNight());
        assertEquals(2, room.getMaxCapacity());
    }

    @Test
    public void testDeleteRoom() {
        Room room = new Room();
        try {
            room = roomService.createRoom(RoomType.Deluxe, Room.BedType.Queen, true, 100, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(RoomType.Deluxe, room.getRoomType());
        assertEquals(Room.BedType.Queen, room.getBedType());
        assertEquals(true, room.getIsAvailable());
        assertEquals(100, room.getPricePerNight());
        assertEquals(2, room.getMaxCapacity());
        
        try {
            roomService.deleteRoom(room.getRoomId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertNull(roomService.getRoomByRoomId(room.getRoomId()));
    }

    @Test
    public void testSetRoomAvailability() {
        Room room = roomRepository.findRoomByRoomId(1);
        room.setIsAvailable(false); // force room to be unavailable
        assertFalse(room.getIsAvailable());

        roomService.setRoomAvailability(1, true);
        assertTrue(room.getIsAvailable());
    }
}
