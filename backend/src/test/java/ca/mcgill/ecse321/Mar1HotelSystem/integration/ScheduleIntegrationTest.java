package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelScheduleRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.CustomHoursRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.OperatingHoursRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.HotelScheduleRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.HotelScheduleResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.CustomHoursRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.CustomHoursResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.CustomHoursMultipleResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.OperatingHoursRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.OperatingHoursResponseDto;

import ca.mcgill.ecse321.Mar1HotelSystem.dto.OperatingHoursMultipleResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;

/**
 * Schedule Integration Tests
 * 
 * @author Emma Friesen (@emma-friesen)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScheduleIntegrationTest {
    @Autowired
    private TestRestTemplate client;

    @Autowired
    private HotelScheduleRepository hotelScheduleRepo;

    @Autowired
    private CustomHoursRepository customHoursRepo;

    @Autowired
    private OperatingHoursRepository operatingHoursRepo;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Clears the database before and after each test
    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        roomRepository.deleteAll();
        hotelRepository.deleteAll();
        hotelScheduleRepo.deleteAll();
        operatingHoursRepo.deleteAll();
        customHoursRepo.deleteAll();
    }

    public int createOperatingHours(OperatingHoursRequestDto request) {
        ResponseEntity<OperatingHoursResponseDto> response = client.postForEntity("/operatingHours/create", request,
                OperatingHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        OperatingHoursResponseDto oh = response.getBody();
        assertNotNull(oh);
        assertEquals(request.getDayOfWeek(), oh.getDayOfWeek());
        assertEquals(request.getOpeningHour(), oh.getOpeningHour());
        assertEquals(request.getClosingHour(), oh.getClosingHour());
        return oh.getOperatingHoursId();
    }

    public void getOperatingHoursByDay(OperatingHoursRequestDto request) {
        DayOfWeek day = request.getDayOfWeek();

        ResponseEntity<OperatingHoursResponseDto> response = client.getForEntity("/operatingHours/day/" + day,
                OperatingHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        OperatingHoursResponseDto oh = response.getBody();
        assertNotNull(oh);
        assertEquals(request.getDayOfWeek(), oh.getDayOfWeek());
        assertEquals(request.getOpeningHour(), oh.getOpeningHour());
        assertEquals(request.getClosingHour(), oh.getClosingHour());
    }

    public void updateOperatingHours(OperatingHoursRequestDto updatedRequest) {
        ResponseEntity<OperatingHoursResponseDto> response = client.postForEntity("/operatingHours/update",
                updatedRequest, OperatingHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        OperatingHoursResponseDto oh = response.getBody();
        assertNotNull(oh);
        assertEquals(updatedRequest.getDayOfWeek(), oh.getDayOfWeek());
        assertEquals(updatedRequest.getOpeningHour(), oh.getOpeningHour());
        assertEquals(updatedRequest.getClosingHour(), oh.getClosingHour());
    }

    public List<OperatingHours> getAllOperatingHours() {
        ResponseEntity<OperatingHoursMultipleResponseDto> response = client.getForEntity("/operatingHours",
                OperatingHoursMultipleResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        OperatingHoursMultipleResponseDto multiOH = response.getBody();
        assertNotNull(multiOH);
        assertNotNull(multiOH.getOperatingHoursList());
        assertEquals(2, multiOH.getOperatingHoursList().size());
        assertEquals(2, operatingHoursRepo.count());

        List<OperatingHours> operatingHoursList = new ArrayList<OperatingHours>();
        for (OperatingHoursResponseDto ohRD : multiOH.getOperatingHoursList()) {
            OperatingHours operatingHoursModel = new OperatingHours(ohRD.getDayOfWeek(), ohRD.getOpeningHour(),
                    ohRD.getClosingHour());
            operatingHoursList.add(operatingHoursModel);
        }

        return operatingHoursList;
    }

    @Test
    public void testCreateUpdateAndGetOperatingHours() {
        OperatingHoursRequestDto request = new OperatingHoursRequestDto(DayOfWeek.Monday, 8, 20);
        OperatingHoursRequestDto updatedRequest = new OperatingHoursRequestDto(DayOfWeek.Monday, 10, 22);

        createOperatingHours(request);
        getOperatingHoursByDay(request);
        updateOperatingHours(updatedRequest);
    }

    @Test
    public void testCreateAndGetAllOH() {
        OperatingHoursRequestDto request1 = new OperatingHoursRequestDto(DayOfWeek.Monday, 8, 20);
        OperatingHoursRequestDto request2 = new OperatingHoursRequestDto(DayOfWeek.Tuesday, 8, 20);

        createOperatingHours(request1);
        createOperatingHours(request2);
        List<OperatingHours> listOH = getAllOperatingHours();
        assertEquals(2, listOH.size());
        assertEquals(request1.getDayOfWeek().toString(), listOH.get(0).getDayOfWeek().toString());
    }

    // custom hours
    public int createCustomHours(CustomHoursRequestDto request) {
        ResponseEntity<CustomHoursResponseDto> response = client.postForEntity("/customHours/create", request,
                CustomHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        CustomHoursResponseDto ch = response.getBody();
        assertNotNull(ch);
        assertEquals(request.getDate(), ch.getDate());
        assertEquals(request.getOpeningHour(), ch.getOpeningHour());
        assertEquals(request.getClosingHour(), ch.getClosingHour());
        return ch.getCustomHoursId();
    }

    @Test
    public void testCreateCustomHours() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();

        CustomHoursRequestDto request = new CustomHoursRequestDto(date, 8, 20);

        createCustomHours(request);
    }

    public void getAllCustomHours() {
        ResponseEntity<CustomHoursMultipleResponseDto> response = client.getForEntity("/customHours",
                CustomHoursMultipleResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, customHoursRepo.count());
    }

    @Test
    public void testGetAllCustomHours() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();

        CustomHoursRequestDto request = new CustomHoursRequestDto(date, 8, 20);

        createCustomHours(request);

        calendar.set(2023, Calendar.FEBRUARY, 21, 0, 0, 0);
        Date date2 = calendar.getTime();

        CustomHoursRequestDto request2 = new CustomHoursRequestDto(date2, 8, 20);

        createCustomHours(request2);
        getAllCustomHours();
    }

    public void updateCustomHours(CustomHoursRequestDto request) {

        Date date = request.getDate();
        ResponseEntity<CustomHoursResponseDto> response = client.postForEntity("/customHours/update", request,
                CustomHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CustomHoursResponseDto ch = response.getBody();
        assertNotNull(ch);
        assertEquals(date, ch.getDate());
        assertEquals(request.getOpeningHour(), ch.getOpeningHour());
        assertEquals(request.getClosingHour(), ch.getClosingHour());
    }

    @Test
    public void testUpdateCustomHours() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();

        CustomHoursRequestDto ch = new CustomHoursRequestDto(date, 8, 20);
        createCustomHours(ch);
        CustomHoursRequestDto request = new CustomHoursRequestDto(date, 10, 22);
        updateCustomHours(request);

    }

    public void getCustomHourById(int id) {
        ResponseEntity<CustomHoursResponseDto> response = client.getForEntity("/customHours/id/" + id,
                CustomHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CustomHoursResponseDto ch = response.getBody();
        assertNotNull(ch);
        assertEquals(id, ch.getCustomHoursId());
    }

    @Test
    public void testGetCustomHourById() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();

        CustomHoursRequestDto ch = new CustomHoursRequestDto(date, 8, 20);
        int ch_id = createCustomHours(ch);
        getCustomHourById(ch_id);
    }

    // --- HOTEL SCHEDULE INTEGRATION TESTS ---

    public void createHotelSchedule(HotelScheduleRequestDto hotelScheduleRequestDto) {
        ResponseEntity<HotelScheduleResponseDto> response = client.postForEntity("/hotelSchedule/create",
                hotelScheduleRequestDto,
                HotelScheduleResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        HotelScheduleResponseDto hs = response.getBody();
        assertNotNull(hs);
        assertEquals(hotelScheduleRequestDto.getYear(), hs.getYear());
        assertEquals(hotelScheduleRequestDto.getOperatingHoursIdList()[0],
                hs.getOperatingHoursList().get(0).getOperatingHoursId());
        assertEquals(hotelScheduleRequestDto.getCustomHoursIdList()[0],
                hs.getCustomHoursList().get(0).getCustomHoursId());
    }

    @Test
    public void testCreateHotelSchedule() {
        OperatingHoursRequestDto oh1 = new OperatingHoursRequestDto(DayOfWeek.Monday, 8, 20);
        OperatingHoursRequestDto oh2 = new OperatingHoursRequestDto(DayOfWeek.Tuesday, 8, 20);
        OperatingHoursRequestDto oh3 = new OperatingHoursRequestDto(DayOfWeek.Wednesday, 8, 16);

        int id_oh1 = createOperatingHours(oh1);
        int id_oh2 = createOperatingHours(oh2);
        int id_oh3 = createOperatingHours(oh3);
        int[] ids_oh = { id_oh1, id_oh2, id_oh3 };

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();
        CustomHoursRequestDto ch1 = new CustomHoursRequestDto(date, 10, 24);

        int id_ch1 = createCustomHours(ch1);
        int[] ids_ch = { id_ch1 };

        HotelScheduleRequestDto hotelScheduleRequest = new HotelScheduleRequestDto(2023, ids_oh, ids_ch);
        createHotelSchedule(hotelScheduleRequest);
    }

    public void getHotelScheduleByYear(int year) {
        ResponseEntity<HotelScheduleResponseDto> response = client.getForEntity("/hotelSchedule/year/" + year,
                HotelScheduleResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        HotelScheduleResponseDto hs = response.getBody();
        assertNotNull(hs);
        assertEquals(year, hs.getYear());
    }

    @Test
    public void testGetHotelScheduleByYear() {
        OperatingHoursRequestDto oh1 = new OperatingHoursRequestDto(DayOfWeek.Monday, 8, 20);
        OperatingHoursRequestDto oh2 = new OperatingHoursRequestDto(DayOfWeek.Tuesday, 8, 20);
        OperatingHoursRequestDto oh3 = new OperatingHoursRequestDto(DayOfWeek.Wednesday, 8, 16);

        int id_oh1 = createOperatingHours(oh1);
        int id_oh2 = createOperatingHours(oh2);
        int id_oh3 = createOperatingHours(oh3);
        int[] ids_oh = { id_oh1, id_oh2, id_oh3 };

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();
        CustomHoursRequestDto ch1 = new CustomHoursRequestDto(date, 10, 24);

        int id_ch1 = createCustomHours(ch1);
        int[] ids_ch = { id_ch1 };

        HotelScheduleRequestDto hotelScheduleRequest = new HotelScheduleRequestDto(2023, ids_oh, ids_ch);
        createHotelSchedule(hotelScheduleRequest);
        getHotelScheduleByYear(2023);
    }
}