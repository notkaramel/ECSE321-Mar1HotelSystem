package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    private Iterable<RoomRequestDto> generateRequests() {
        roomReqList = new ArrayList<RoomRequestDto>();
        for (Room.RoomType roomType : Room.RoomType.values()) {
            for (Room.BedType bedType : Room.BedType.values()) {
                for (int i = 0; i < 4; i++) {
                    RoomRequestDto roomReq = new RoomRequestDto(roomType, bedType, true, 100, 2);
                    roomReqList.add(roomReq);
                }
            }
        }
        return roomReqList;
    }

    /*
     * Helper function: turn a list of RoomResponseDto into a list of Room
     */
    private List<Room> turnRoomResponseDtoToRoom(List<RoomResponseDto> roomResponseDtoList) {
        List<Room> roomList = new ArrayList<Room>();
        for (RoomResponseDto roomResponseDto : roomResponseDtoList) {
            Hotel hotel = hotelRepository.findHotelByHotelName("Mar-1 Hotel");
            Room room = new Room(roomResponseDto.getRoomType(), roomResponseDto.getBedType(),
                    roomResponseDto.getIsAvailable(), roomResponseDto.getPricePerNight(),
                    roomResponseDto.getMaxCapacity(), hotel);
            roomList.add(room);
        }
        return roomList;
    }

    /*
     * Send a POST request: /room/create
     */
    public int createRoomFromRequest(RoomRequestDto roomRequestDto) {
        ResponseEntity<RoomResponseDto> res = roomClient.postForEntity("/room/create", roomRequestDto,
                RoomResponseDto.class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(HttpStatus.CREATED, res.getStatusCode());

        return res.getBody().getRoomId();
    }

    /*
     * Send a GET request: /rooms/id/{roomId}
     */
    public void retrieveRoomById(int id) {
        ResponseEntity<RoomResponseDto> res = roomClient.getForEntity("/room/id/" + id, RoomResponseDto.class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(id, res.getBody().getRoomId());
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    /*
     * Send a GET request: /rooms
     */
    public List<Room> retrieveAllRooms() {
        ResponseEntity<MultipleRoomDto> res = roomClient.getForEntity("/rooms", MultipleRoomDto.class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(HttpStatus.OK, res.getStatusCode());

        List<RoomResponseDto> roomList = (List<RoomResponseDto>) res.getBody().getRoomList();
        List<Room> roomListInDb = new ArrayList<Room>();
        return turnRoomResponseDtoToRoom(roomList);
    }

    public List<Room> retrieveAllRoomsByRoomType(Room.RoomType rt) {
        ResponseEntity<MultipleRoomDto> res = roomClient.getForEntity("/rooms/roomType/", MultipleRoomDto.class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(HttpStatus.OK, res.getStatusCode());

        List<RoomResponseDto> roomList = (List<RoomResponseDto>) res.getBody().getRoomList();
        ;
        return turnRoomResponseDtoToRoom(roomList);
    }

    public List<Room> retrieveAllRoomByBedType(Room.BedType bt) {
        ResponseEntity<MultipleRoomDto> res = roomClient.getForEntity("/rooms/bedType/", MultipleRoomDto.class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(HttpStatus.OK, res.getStatusCode());

        List<RoomResponseDto> roomList = (List<RoomResponseDto>) res.getBody().getRoomList();
        ;
        return turnRoomResponseDtoToRoom(roomList);
    }

    // ---------- TEST ------------ //

    @Test
    public void testCreateAndGetRoomById() {
        // Singular Room Request Test
        generateRequests();
        // Here I only get one request from the list
        int id = createRoomFromRequest(roomReqList.get(0));
        assertEquals(1, roomRepository.count());
        retrieveRoomById(id);
    }

    @Test
    public void testCreateMultipleAndGetAllRooms() {
        // Multiple Room Requests Test
        generateRequests();
        for (RoomRequestDto roomReq : roomReqList) {
            createRoomFromRequest(roomReq);
        }
        retrieveAllRooms();
        assertEquals(36, roomRepository.count());
    }

    @Test
    public void testCreateAndGetAllByRoomTypes() {
        generateRequests();
        for (RoomRequestDto roomReq : roomReqList) {
            createRoomFromRequest(roomReq);
        }

        for (Room.RoomType roomType : Room.RoomType.values()) {
            ResponseEntity<MultipleRoomDto> res = roomClient.getForEntity("/rooms/roomType/" + roomType,
                    MultipleRoomDto.class);
            assertNotNull(res);
            assertNotNull(res.getBody());
            assertEquals(HttpStatus.OK, res.getStatusCode());

            List<RoomResponseDto> roomList = (List<RoomResponseDto>) res.getBody().getRoomList();
            for (RoomResponseDto room : roomList) {
                assertEquals(roomType, room.getRoomType());
            }
        }
    }

    @Test
    public void testCreateAndGetAllByBedTypes() {
        generateRequests();
        for (RoomRequestDto roomReq : roomReqList) {
            createRoomFromRequest(roomReq);
        }

        for (Room.BedType bedType : Room.BedType.values()) {
            ResponseEntity<MultipleRoomDto> res = roomClient.getForEntity("/rooms/bedType/" + bedType,
                    MultipleRoomDto.class);
            assertNotNull(res);
            assertNotNull(res.getBody());
            assertEquals(HttpStatus.OK, res.getStatusCode());

            List<RoomResponseDto> roomList = (List<RoomResponseDto>) res.getBody().getRoomList();
            for (RoomResponseDto room : roomList) {
                assertEquals(bedType, room.getBedType());
            }
        }
    }

    @Test
    public void testDeleteRoomById() {
        generateRequests();
        int id1 = createRoomFromRequest(roomReqList.get(5));
        int id2 = createRoomFromRequest(roomReqList.get(12));
        assertEquals(2, roomRepository.count());
        retrieveRoomById(id1);
        retrieveRoomById(id2);

        roomClient.delete("/room/delete/" + id1);
        assertEquals(1, roomRepository.count());
    }

    @Test
    public void testUpdateRoomById() {
        generateRequests();
        int id = createRoomFromRequest(roomReqList.get(0));
        assertEquals(1, roomRepository.count());
        retrieveRoomById(id);

        RoomRequestDto roomReq = new RoomRequestDto(Room.RoomType.Deluxe, Room.BedType.Doubles, false, 200, 4);
        roomClient.put("/room/update/" + id, roomReq);
        retrieveRoomById(id);
    }
}
