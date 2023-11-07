package ca.mcgill.ecse321.Mar1HotelSystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

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
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
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

        // Mock finding methods
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

        // Mock saving methods
        lenient().when(roomRepository.save(isA(Room.class))).then((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });
    }


    @Test
    public void testGetAllRooms() {
        ArrayList<Room> rooms = new ArrayList<Room>();
        rooms = (ArrayList<Room>) roomService.getAllRooms();
        assertEquals(6, rooms.size());
    }

    @Test
    public void testGetAllRoomsByRoomType() {
        ArrayList<Room> rooms = new ArrayList<Room>();
        rooms = (ArrayList<Room>) roomService.getRoomsByRoomType(RoomType.Deluxe);
        assertEquals(2, rooms.size());
    }

    @Test
    public void testGetRoomByRoomId() {
        Room room = new Room();
        room = roomService.getRoomByRoomId(1);
        assertNotNull(room);
        assertEquals(1, room.getRoomId());
    }

    @Test
    public void testCreateRoom() {
        Room room = new Room();
        room = roomService.createRoom(RoomType.Deluxe, Room.BedType.Queen, true, 100, 2);

        assertEquals(RoomType.Deluxe, room.getRoomType());
        assertEquals(Room.BedType.Queen, room.getBedType());
        assertEquals(true, room.getIsAvailable());
        assertEquals(100, room.getPricePerNight());
        assertEquals(2, room.getMaxCapacity());
    }

    @Test
    public void testDeleteRoomById() {
        // Trying to delete existing (mocked) room 1
        Room deletedRoom = roomService.deleteRoomByRoomId(1);
        assertNotNull(deletedRoom);
        assertEquals(1, deletedRoom.getRoomId());
    }

    @Test
    public void testSetRoomAvailability() {
        Room room = roomRepository.findRoomByRoomId(1);
        assertTrue(room.getIsAvailable()); // Room 1 should be true by mocking

        // Set room to unavailable
        boolean setBool = roomService.setRoomUnavialable(1);
        assertTrue(setBool);
        assertFalse(room.getIsAvailable());

        // Set room to available again
        setBool = roomService.setRoomAvailable(1);
        assertTrue(setBool);
        assertTrue(room.getIsAvailable());
    }
    
    /* ----------- INVALID TESTS ----------- */
    @Test
    public void testCreateRoomButHotelIsNull() {
        Hotel hotel = null;
        Room nullTestRoom = new Room(RoomType.Regular, BedType.Doubles, false, 10, 2, hotel);

        assertNull(hotel);
        assertNull(nullTestRoom.getHotel());

        Room room = roomService.createRoom(nullTestRoom.getRoomType(), nullTestRoom.getBedType(), nullTestRoom.getIsAvailable(), nullTestRoom.getPricePerNight(), nullTestRoom.getMaxCapacity());
        assertNotNull(room.getHotel());
    }

    @Test
    public void testGetRoomByRoomIdButInvalidId() {
        try {
            // There is no room with Id 7 being mocked
            roomService.getRoomByRoomId(7);
        } catch (Mar1HotelSystemException e) {
            assertEquals(e.getStatus(), HttpStatus.BAD_REQUEST);
            assertEquals(e.getMessage(), "Can't find room with id {7}");
        }
    }
}
