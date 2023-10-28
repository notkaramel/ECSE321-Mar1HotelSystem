package ca.mcgill.ecse321.Mar1HotelSystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.service.RoomService;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
    @InjectMocks
    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    // Create DAO mock object for room
    @BeforeEach
    public void setMockOutput() {
        lenient().when(roomRepository.findRoomsByRoomType(isA(Room.RoomType.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(RoomType.Deluxe)) {
                ArrayList<Room> rooms = new ArrayList<Room>();
                Room room = new Room(Room.RoomType.Deluxe, Room.BedType.Queen, true, 100, 2, null);
                rooms.add(room);
                return rooms;
            } else {
                return null;
            }
        });

        lenient().when(roomRepository.findRoomByRoomId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(Integer.valueOf(1))) {
                Room room = new Room(Room.RoomType.Deluxe, Room.BedType.Queen, true, 100, 2, null);
                room.setRoomId(1);
                return room;
            } else {
                return null;
            }
        });
    }

    @Test
    public void testGetAllRooms() {
        ArrayList<Room> rooms = new ArrayList<Room>();
        try {
            rooms = (ArrayList<Room>) roomService.getRoomsByRoomType(RoomType.Deluxe);
            rooms.add(roomRepository.findRoomByRoomId(1));
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
            room = roomService.createRoom(RoomType.Deluxe, Room.BedType.Queen, true, 100, 2, null);
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
            room = roomService.createRoom(RoomType.Deluxe, Room.BedType.Queen, true, 100, 2, null);
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
}
