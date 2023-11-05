package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomIntegrationTest {
    @Autowired
    private TestRestTemplate roomClient;

    @Autowired
    private RoomRepository roomRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        roomRepository.deleteAll();
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
        ResponseEntity<RoomResponseDto> res = roomClient.getForEntity("/rooms/" + id, RoomResponseDto.class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(id, res.getBody().getRoomId());
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void testGetAllRooms() {
        int id = testCreateRoom();
        testGetRoomById(id);
    }

    
}
