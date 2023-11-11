package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

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
import ca.mcgill.ecse321.Mar1HotelSystem.dao.OperatingHoursRepository;

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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// Reuse the same class for all the tests (instead of creating a new class each
// time).
public class ScheduleIntegrationTest {
    @Autowired
    private TestRestTemplate client;

    @Autowired
    private HotelScheduleRepository hotelScheduleRepo;

    @Autowired
    private CustomHoursRepository customHoursRepo;

    @Autowired
    private OperatingHoursRepository operatingHoursRepo;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        hotelScheduleRepo.deleteAll();
        customHoursRepo.deleteAll();
        operatingHoursRepo.deleteAll();
    }

    public void CreateOperatingHours(OperatingHoursRequestDto request) {

        ResponseEntity<OperatingHoursResponseDto> response = client.postForEntity("/operatingHours/create", request,
                OperatingHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(request.getDayOfWeek(), response.getBody().getDayOfWeek());
        assertEquals(request.getOpeningHour(), response.getBody().getOpeningHour());
        assertEquals(request.getClosingHour(), response.getBody().getClosingHour());
    }

    public void GetOperatingHoursByDay(OperatingHoursRequestDto request) {
        DayOfWeek day = request.getDayOfWeek();

        ResponseEntity<OperatingHoursResponseDto> response = client.getForEntity("/operatingHours/day/" + day,
                OperatingHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(request.getDayOfWeek(), response.getBody().getDayOfWeek());
        assertEquals(request.getOpeningHour(), response.getBody().getOpeningHour());
        assertEquals(request.getClosingHour(), response.getBody().getClosingHour());
    }

    public void UpdateOperatingHours(OperatingHoursRequestDto updatedRequest) {
        DayOfWeek day = updatedRequest.getDayOfWeek();
        ResponseEntity<OperatingHoursResponseDto> response = client.postForEntity("/operatingHours/update",
                updatedRequest, OperatingHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedRequest.getDayOfWeek(), response.getBody().getDayOfWeek());
        assertEquals(updatedRequest.getOpeningHour(), response.getBody().getOpeningHour());
        assertEquals(updatedRequest.getClosingHour(), response.getBody().getClosingHour());
    }

    public void GetAllOperatingHours() {

        ResponseEntity<OperatingHoursMultipleResponseDto> response = client.getForEntity("/operatingHours",
                OperatingHoursMultipleResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, operatingHoursRepo.count());
    }

    @Test
    public void testCreateUpdateAndGetOperatingHours() {
        OperatingHoursRequestDto request = new OperatingHoursRequestDto();
        request.setDayOfWeek(OperatingHours.DayOfWeek.Monday);
        request.setOpeningHour(8);
        request.setClosingHour(20);

        OperatingHoursRequestDto updatedRequest = new OperatingHoursRequestDto();
        updatedRequest.setDayOfWeek(OperatingHours.DayOfWeek.Monday);
        updatedRequest.setOpeningHour(10);
        updatedRequest.setClosingHour(22);

        CreateOperatingHours(request);
        GetOperatingHoursByDay(request);
        UpdateOperatingHours(updatedRequest);
    }

    @Test
    public void testGetAllOH() {
        OperatingHoursRequestDto request = new OperatingHoursRequestDto();
        request.setDayOfWeek(OperatingHours.DayOfWeek.Monday);
        request.setOpeningHour(8);
        request.setClosingHour(20);
        CreateOperatingHours(request);

        OperatingHoursRequestDto request2 = new OperatingHoursRequestDto();
        request2.setDayOfWeek(OperatingHours.DayOfWeek.Tuesday);
        request2.setOpeningHour(8);
        request2.setClosingHour(20);
        CreateOperatingHours(request2);

        GetAllOperatingHours();
    }

    // custom hours
    public void CreateCustomHours(CustomHoursRequestDto request) {
        ResponseEntity<CustomHoursResponseDto> response = client.postForEntity("/customHours/create", request,
                CustomHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(request.getDate(), response.getBody().getDate());
        assertEquals(request.getOpeningHour(), response.getBody().getOpeningHour());
        assertEquals(request.getClosingHour(), response.getBody().getClosingHour());
    }

    @Test
    public void testCreateCustomHours() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();

        CustomHoursRequestDto request = new CustomHoursRequestDto();
        request.setDate(date);
        request.setOpeningHour(8);
        request.setClosingHour(20);

        CreateCustomHours(request);
    }

    public void GetAllCustomHours() {
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
        calendar.set(2023, Calendar.FEBRUARY, 20, 0, 0, 0);
        Date date = calendar.getTime();

        CustomHoursRequestDto request = new CustomHoursRequestDto();
        request.setDate(date);
        request.setOpeningHour(8);
        request.setClosingHour(20);

        CreateCustomHours(request);

        calendar.set(2023, Calendar.FEBRUARY, 21, 0, 0, 0);
        Date date2 = calendar.getTime();

        CustomHoursRequestDto request2 = new CustomHoursRequestDto();
        request2.setDate(date2);
        request2.setOpeningHour(8);
        request2.setClosingHour(20);

        CreateCustomHours(request2);

        GetAllCustomHours();
    }

    public void updateCustomHours(CustomHoursRequestDto request) {

        Date date = request.getDate();
        ResponseEntity<CustomHoursResponseDto> response = client.postForEntity("/customHours/update", request,
                CustomHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(date, response.getBody().getDate());
        assertEquals(request.getOpeningHour(), response.getBody().getOpeningHour());
        assertEquals(request.getClosingHour(), response.getBody().getClosingHour());
    }

    @Test
    public void testUpdateCustomHours() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();

        CustomHoursRequestDto ch = new CustomHoursRequestDto();
        ch.setDate(date);
        ch.setOpeningHour(8);
        ch.setClosingHour(20);

        CreateCustomHours(ch);

        CustomHoursRequestDto request = new CustomHoursRequestDto();
        request.setDate(date);
        request.setOpeningHour(10);
        request.setClosingHour(22);

        updateCustomHours(request);

    }

    // hotel schedule

    public void deleteCustomHourByDate(CustomHoursRequestDto request) {
        Date date = request.getDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        ResponseEntity<CustomHoursResponseDto> response = client.postForEntity("customHours/{date}" + dateString,
                request, CustomHoursResponseDto.class); // TODO: fix issue with accessing based on date

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(date, response.getBody().getDate());
        assertEquals(request.getOpeningHour(), response.getBody().getOpeningHour());
        assertEquals(request.getClosingHour(), response.getBody().getClosingHour());
    }

    @Test // TODO: try to use ID instead of date
    public void testDeleteCustomHourByDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();

        CustomHoursRequestDto ch = new CustomHoursRequestDto();
        ch.setDate(date);
        ch.setOpeningHour(8);
        ch.setClosingHour(20);

        CreateCustomHours(ch);

        deleteCustomHourByDate(ch);
    }

    public void createHotelSchedule(HotelScheduleRequestDto request) {

        ResponseEntity<HotelScheduleResponseDto> response = client.postForEntity("/hotelSchedule/create", request,
                HotelScheduleResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(request.getYear(), response.getBody().getYear());
        assertEquals(request.getOperatingHoursIdList(), response.getBody().getOperatingHoursList());
        assertEquals(request.getCustomHoursIdList(), response.getBody().getCustomHoursList());
    }

    // @Test
    // public void testCreateHotelSchedule(){
    // Calendar calendar = Calendar.getInstance();
    // calendar.set(2023, Calendar.FEBRUARY, 20);
    // Date date1 = calendar.getTime();

    // CustomHours ch1 = new CustomHours();
    // ch1.setDate(date1);
    // ch1.setOpeningHour(10);
    // ch1.setClosingHour(11);

    // OperatingHours oh1 = new OperatingHours();
    // oh1.setDayOfWeek(OperatingHours.DayOfWeek.Monday);
    // oh1.setOpeningHour(8);
    // oh1.setClosingHour(20);

    // OperatingHours oh2 = new OperatingHours();
    // oh2.setDayOfWeek(OperatingHours.DayOfWeek.Tuesday);
    // oh2.setOpeningHour(8);
    // oh2.setClosingHour(20);

    // OperatingHours[] operatingHoursArray = {oh1, oh2};
    // CustomHours[] customHoursArray = {ch1};

    // HotelScheduleRequestDto request = new HotelScheduleRequestDto();
    // request.setOperatingHoursList(operatingHoursArray);
    // request.setCustomHoursList(customHoursArray);
    // request.setYear(2023);

    // createHotelSchedule(request);

    // }

    public void getHotelScheduleByYear(HotelScheduleRequestDto request) {
        int year = request.getYear();

        ResponseEntity<HotelScheduleResponseDto> response = client.getForEntity("/hotelSchedule/year/" + year,
                HotelScheduleResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(request.getYear(), response.getBody().getYear());
        assertEquals(request.getOperatingHoursIdList(), response.getBody().getOperatingHoursList());
        assertEquals(request.getCustomHoursIdList(), response.getBody().getCustomHoursList());
    }

    // @Test
    // public void testGetHotelScheduleByYear(){
    // Calendar calendar = Calendar.getInstance();
    // calendar.set(2023, Calendar.FEBRUARY, 20);
    // Date date1 = calendar.getTime();

    // CustomHours ch1 = new CustomHours();
    // ch1.setDate(date1);
    // ch1.setOpeningHour(10);
    // ch1.setClosingHour(11);

    // OperatingHours oh1 = new OperatingHours();
    // oh1.setDayOfWeek(OperatingHours.DayOfWeek.Monday);
    // oh1.setOpeningHour(8);
    // oh1.setClosingHour(20);

    // OperatingHours oh2 = new OperatingHours();
    // oh2.setDayOfWeek(OperatingHours.DayOfWeek.Tuesday);
    // oh2.setOpeningHour(8);
    // oh2.setClosingHour(20);

    // OperatingHours[] operatingHoursArray = {oh1, oh2};
    // CustomHours[] customHoursArray = {ch1};

    // HotelScheduleRequestDto request = new HotelScheduleRequestDto();
    // request.setOperatingHoursList(operatingHoursArray);
    // request.setCustomHoursList(customHoursArray);
    // request.setYear(2023);

    // createHotelSchedule(request);
    // getHotelScheduleByYear(request);
    // }
}