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

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        roomRepository.deleteAll();
        hotelRepository.deleteAll();
    }

    public int testCreateRoom() {
        RoomRequestDto testRoom = new RoomRequestDto(Room.RoomType.Deluxe, Room.BedType.Doubles, true, 100, 1);
        ResponseEntity<RoomResponseDto> res = roomClient.postForEntity("/createRoom", testRoom, RoomResponseDto.class);
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
        int id = testCreateRoom();
        testGetRoomById(id);
    }

    @Test
    public void testCreateMultipleAndGetAllRooms() {
        testCreateRoom();
        testCreateRoom();
        testGetAllRooms();
    }
}
