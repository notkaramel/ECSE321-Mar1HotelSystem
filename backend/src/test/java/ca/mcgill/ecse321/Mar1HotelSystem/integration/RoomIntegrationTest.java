package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.MultipleRoomDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomIntegrationTest {
    @Autowired
    private TestRestTemplate roomClient;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;


    private ArrayList<RoomRequestDto> roomReqList;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        roomRepository.deleteAll();
    }

    private Iterable<RoomRequestDto> initializeRoomRequests() {
        roomReqList = new ArrayList<RoomRequestDto>();
        for(Room.RoomType roomType : Room.RoomType.values()) {
            for(Room.BedType bedType : Room.BedType.values()) {
                for(int i = 0; i < 3; i++) {
                    RoomRequestDto roomReq = new RoomRequestDto(roomType, bedType, true, 100, 2);
                    roomReqList.add(roomReq);
                }
            }
        }
        return roomReqList;
    }


    public int testCreateRoom(RoomRequestDto roomRequestDto) {
        ResponseEntity<RoomResponseDto> res = roomClient.postForEntity("/room/create", roomRequestDto, RoomResponseDto.class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(HttpStatus.CREATED, res.getStatusCode());

        return res.getBody().getRoomId();
    }

    public void testGetRoomById(int id) {
        ResponseEntity<RoomResponseDto> res = roomClient.getForEntity("/rooms/id/" + id, RoomResponseDto.class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(id, res.getBody().getRoomId());
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    public List<Room> testGetAllRooms() {
        ResponseEntity<MultipleRoomDto> res = roomClient.getForEntity("/rooms", MultipleRoomDto.class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(HttpStatus.OK, res.getStatusCode());

        List<RoomResponseDto> roomList = (List<RoomResponseDto>) res.getBody().getRoomList();
        List<Room> roomListInDb = new ArrayList<Room>();
        for (RoomResponseDto room : roomList) {
            Hotel hotel = hotelRepository.findHotelByHotelName("Mar-1 Hotel");
            Room roomInDb = new Room(room.getRoomType(), room.getBedType(), room.getIsAvailable(), room.getPricePerNight(), room.getMaxCapacity(), hotel);
            roomListInDb.add(roomInDb);
        }
        return roomListInDb;
    }

    @Test
    public void testCreateAndGetRoomById() {
        initializeRoomRequests();
        int id = testCreateRoom(roomReqList.get(0));
        assertEquals(1, roomRepository.count());
        testGetRoomById(id);
    }

    @Test
    public void testCreateMultipleAndGetAllRooms() {
        initializeRoomRequests();
        for(RoomRequestDto roomReq : roomReqList) {
            testCreateRoom(roomReq);
        }
        testGetAllRooms();
    }

    public List<Room> allRoomsByRoomType(List<Room> allRooms, Room.RoomType rt) {
        List<Room> roomsByRoomType = new ArrayList<Room>();
        for(Room room : allRooms) {
            if(room.getRoomType().equals(rt)) {
                roomsByRoomType.add(room);
            }
        }
        return roomsByRoomType;
    }

    @Test
    public void testCreateAndGetAllByTypes() {
        initializeRoomRequests();
        for(RoomRequestDto roomReq : roomReqList) {
            testCreateRoom(roomReq);
        }

        for(Room.RoomType roomType : Room.RoomType.values()) {
            ResponseEntity<MultipleRoomDto> res = roomClient.getForEntity("/rooms/roomType/" + roomType, MultipleRoomDto.class);
            assertNotNull(res);
            assertNotNull(res.getBody());
            assertEquals(HttpStatus.OK, res.getStatusCode());

            List<RoomResponseDto> roomList = (List<RoomResponseDto>) res.getBody().getRoomList();
            for(RoomResponseDto room : roomList) {
                assertEquals(roomType, room.getRoomType());
            }
        }
    }
}
