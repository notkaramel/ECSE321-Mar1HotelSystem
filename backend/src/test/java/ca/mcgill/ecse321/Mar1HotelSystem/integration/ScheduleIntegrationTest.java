package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.only;

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
import ca.mcgill.ecse321.Mar1HotelSystem.dao.OperatingHoursRepository;

import ca.mcgill.ecse321.Mar1HotelSystem.dto.HotelScheduleRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.HotelScheduleResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.HotelScheduleMultipleResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.CustomHoursRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.CustomHoursResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.CustomHoursMultipleResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.OperatingHoursRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.OperatingHoursResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.OperatingHoursResponseDto.DayOfWeekDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.OperatingHoursMultipleResponseDto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;
import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;

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

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        hotelScheduleRepo.deleteAll();
        customHoursRepo.deleteAll();
        operatingHoursRepo.deleteAll();
    }

    @Test
    public void testCreateOperatingHours() {
        OperatingHoursRequestDto request = new OperatingHoursRequestDto();
        request.setDayOfWeek(OperatingHours.DayOfWeek.Monday);
        request.setOpeningHour(8);
        request.setClosingHour(20);

        ResponseEntity<OperatingHoursResponseDto> response = client.postForEntity("/operatingHours", request, OperatingHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(OperatingHours.DayOfWeek.Monday, response.getBody().getDayOfWeekDto());
        assertEquals(8, response.getBody().getOpeningHour());
        assertEquals(20, response.getBody().getClosingHour());
    }

    @Test
    public void testGetOperatingHoursByDay() {
        OperatingHours oh = new OperatingHours();
        oh.setDayOfWeek(OperatingHours.DayOfWeek.Monday);
        oh.setOpeningHour(8);
        oh.setClosingHour(20);
        operatingHoursRepo.save(oh);

        ResponseEntity<OperatingHoursResponseDto> response = client.getForEntity("/operatingHours/Monday", OperatingHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(OperatingHours.DayOfWeek.Monday, response.getBody().getDayOfWeekDto());
        assertEquals(8, response.getBody().getOpeningHour());
        assertEquals(20, response.getBody().getClosingHour());
    }

    @Test
    public void testGetAllOperatingHours() {
        OperatingHours oh1 = new OperatingHours();
        oh1.setDayOfWeek(OperatingHours.DayOfWeek.Monday);
        oh1.setOpeningHour(8);
        oh1.setClosingHour(20);
        operatingHoursRepo.save(oh1);

        OperatingHours oh2 = new OperatingHours();
        oh2.setDayOfWeek(OperatingHours.DayOfWeek.Tuesday);
        oh2.setOpeningHour(8);
        oh2.setClosingHour(20);
        operatingHoursRepo.save(oh2);

        ResponseEntity<OperatingHoursMultipleResponseDto> response = client.getForEntity("/operatingHours", OperatingHoursMultipleResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(OperatingHours.DayOfWeek.Monday, response.getBody().getAllOperatingHours());

        assertEquals(DayOfWeekDto.Monday, response.getBody().getAllOperatingHours().iterator().next().getDayOfWeekDto());
        assertEquals(8, response.getBody().getAllOperatingHours().iterator().next().getOpeningHour());
        assertEquals(20, response.getBody().getAllOperatingHours().iterator().next().getClosingHour());
    }

    @Test
    public void testUpdateOperatingHours() {
        OperatingHours oh = new OperatingHours();
        oh.setDayOfWeek(OperatingHours.DayOfWeek.Monday);
        oh.setOpeningHour(8);
        oh.setClosingHour(20);
        operatingHoursRepo.save(oh);

        OperatingHoursRequestDto request = new OperatingHoursRequestDto();
        request.setDayOfWeek(OperatingHours.DayOfWeek.Monday);
        request.setOpeningHour(10);
        request.setClosingHour(22);

        ResponseEntity<OperatingHoursResponseDto> response = client.postForEntity("/operatingHours", request, OperatingHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(OperatingHours.DayOfWeek.Monday, response.getBody().getDayOfWeekDto());
        assertEquals(10, response.getBody().getOpeningHour());
        assertEquals(22, response.getBody().getClosingHour());
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

        ResponseEntity<CustomHoursResponseDto> response = client.postForEntity("/customHours", request, CustomHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(date, response.getBody().getDate());
        assertEquals(8, response.getBody().getOpeningHour());
        assertEquals(20, response.getBody().getClosingHour());
    }

    @Test
    public void testGetCustomHoursByDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();
        
        CustomHours ch = new CustomHours();
        ch.setDate(date);
        ch.setOpeningHour(8);
        ch.setClosingHour(20);
        customHoursRepo.save(ch);

        ResponseEntity<CustomHoursResponseDto> response = client.getForEntity("/customHours/2023-02-20", CustomHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(date, response.getBody().getDate());
        assertEquals(8, response.getBody().getOpeningHour());
        assertEquals(20, response.getBody().getClosingHour());
    }

    @Test
    public void testGetAllCustomHours() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date1 = calendar.getTime();
        
        CustomHours ch1 = new CustomHours();
        ch1.setDate(date1);
        ch1.setOpeningHour(8);
        ch1.setClosingHour(20);
        customHoursRepo.save(ch1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2023, Calendar.FEBRUARY, 21);
        Date date2 = calendar2.getTime();
        
        CustomHours ch2 = new CustomHours();
        ch2.setDate(date2);
        ch2.setOpeningHour(8);
        ch2.setClosingHour(20);
        customHoursRepo.save(ch2);

        ResponseEntity<CustomHoursMultipleResponseDto> response = client.getForEntity("/customHours", CustomHoursMultipleResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(date1, response.getBody().getAllCustomHours());

        assertEquals(date1, response.getBody().getAllCustomHours().iterator().next().getDate());
        assertEquals(8, response.getBody().getAllCustomHours().iterator().next().getOpeningHour());
        assertEquals(20, response.getBody().getAllCustomHours().iterator().next().getClosingHour());
    }

    @Test
    public void testUpdateCustomHours() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();
        
        CustomHours ch = new CustomHours();
        ch.setDate(date);
        ch.setOpeningHour(8);
        ch.setClosingHour(20);
        customHoursRepo.save(ch);

        CustomHoursRequestDto request = new CustomHoursRequestDto();
        request.setDate(date);
        request.setOpeningHour(10);
        request.setClosingHour(22);

        ResponseEntity<CustomHoursResponseDto> response = client.postForEntity("/customHours", request, CustomHoursResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(date, response.getBody().getDate());
        assertEquals(10, response.getBody().getOpeningHour());
        assertEquals(22, response.getBody().getClosingHour());
    }

    @Test
    public void testDeleteCustomHours() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();
        
        CustomHours ch = new CustomHours();
        ch.setDate(date);
        ch.setOpeningHour(8);
        ch.setClosingHour(20);
        customHoursRepo.save(ch);
        customHoursRepo.delete(ch);

        ResponseEntity<CustomHoursResponseDto> response = client.getForEntity("/customHours/2023-02-20", CustomHoursResponseDto.class);

        assertNull(response);
    }

    @Test
    public void testCreateHotelSchedule() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date1 = calendar.getTime();
        
        CustomHours ch1 = new CustomHours();
        ch1.setDate(date1);
        ch1.setOpeningHour(10);
        ch1.setClosingHour(11);

        OperatingHours oh1 = new OperatingHours();
        oh1.setDayOfWeek(OperatingHours.DayOfWeek.Monday);
        oh1.setOpeningHour(8);
        oh1.setClosingHour(20);

        OperatingHours oh2 = new OperatingHours();
        oh2.setDayOfWeek(OperatingHours.DayOfWeek.Tuesday);
        oh2.setOpeningHour(8);
        oh2.setClosingHour(20);

        OperatingHours[] operatingHoursArray = {oh1, oh2};
        CustomHours[] customHoursArray = {ch1};

        HotelScheduleRequestDto request = new HotelScheduleRequestDto();
        request.setOperatingHoursList(operatingHoursArray);
        request.setCustomHoursList(customHoursArray);
        request.setYear(2023);

        ResponseEntity<HotelScheduleResponseDto> response = client.postForEntity("/hotelSchedule", request, HotelScheduleResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2023, response.getBody().getYear());
        assertEquals(operatingHoursArray, response.getBody().getOperatingHoursList());
        assertEquals(customHoursArray, response.getBody().getCustomHoursList());
    }

    @Test
    public void testGetHotelScheduleByYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date1 = calendar.getTime();
        
        CustomHours ch1 = new CustomHours();
        ch1.setDate(date1);
        ch1.setOpeningHour(10);
        ch1.setClosingHour(11);

        OperatingHours oh1 = new OperatingHours();
        oh1.setDayOfWeek(OperatingHours.DayOfWeek.Monday);
        oh1.setOpeningHour(8);
        oh1.setClosingHour(20);

        OperatingHours oh2 = new OperatingHours();
        oh2.setDayOfWeek(OperatingHours.DayOfWeek.Tuesday);
        oh2.setOpeningHour(8);
        oh2.setClosingHour(20);

        OperatingHours[] operatingHoursArray = {oh1, oh2};
        CustomHours[] customHoursArray = {ch1};

        HotelSchedule hs = new HotelSchedule();
        hs.setYear(2023);
        hs.setOperatingHours(operatingHoursArray);
        hs.setCustomHours(customHoursArray);
        hotelScheduleRepo.save(hs);

        ResponseEntity<HotelScheduleResponseDto> response = client.getForEntity("/hotelSchedule/2023", HotelScheduleResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2023, response.getBody().getYear());
        assertEquals(operatingHoursArray, response.getBody().getOperatingHoursList());
        assertEquals(customHoursArray, response.getBody().getCustomHoursList());
    }

    @Test
    public void testGetAllHotelSchedule() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date1 = calendar.getTime();
        
        CustomHours ch1 = new CustomHours();
        ch1.setDate(date1);
        ch1.setOpeningHour(10);
        ch1.setClosingHour(11);

        OperatingHours oh1 = new OperatingHours();
        oh1.setDayOfWeek(OperatingHours.DayOfWeek.Monday);
        oh1.setOpeningHour(8);
        oh1.setClosingHour(20);

        OperatingHours oh2 = new OperatingHours();
        oh2.setDayOfWeek(OperatingHours.DayOfWeek.Tuesday);
        oh2.setOpeningHour(8);
        oh2.setClosingHour(20);

        OperatingHours[] operatingHoursArray = {oh1, oh2};
        CustomHours[] customHoursArray = {ch1};

        HotelSchedule hs = new HotelSchedule();
        hs.setYear(2023);
        hs.setOperatingHours(operatingHoursArray);
        hs.setCustomHours(customHoursArray);
        hotelScheduleRepo.save(hs);

        ResponseEntity<HotelScheduleMultipleResponseDto> response = client.getForEntity("/hotelSchedule", HotelScheduleMultipleResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2023, response.getBody().getAllHotelSchedule());

        assertEquals(2023, response.getBody().getAllHotelSchedule().iterator().next().getYear());
        assertEquals(operatingHoursArray, response.getBody().getAllHotelSchedule().iterator().next().getOperatingHoursList());
        assertEquals(customHoursArray, response.getBody().getAllHotelSchedule().iterator().next().getCustomHoursList());
    }

    @Test
    public void testDeleteHotelSchedule() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date1 = calendar.getTime();
        
        CustomHours ch1 = new CustomHours();
        ch1.setDate(date1);
        ch1.setOpeningHour(10);
        ch1.setClosingHour(11);

        OperatingHours oh1 = new OperatingHours();
        oh1.setDayOfWeek(OperatingHours.DayOfWeek.Monday);
        oh1.setOpeningHour(8);
        oh1.setClosingHour(20);

        OperatingHours oh2 = new OperatingHours();
        oh2.setDayOfWeek(OperatingHours.DayOfWeek.Tuesday);
        oh2.setOpeningHour(8);
        oh2.setClosingHour(20);

        OperatingHours[] operatingHoursArray = {oh1, oh2};
        CustomHours[] customHoursArray = {ch1};

        HotelSchedule hs = new HotelSchedule();
        hs.setYear(2023);
        hs.setOperatingHours(operatingHoursArray);
        hs.setCustomHours(customHoursArray);
        hotelScheduleRepo.save(hs);
        hotelScheduleRepo.delete(hs);

        ResponseEntity<HotelScheduleResponseDto> response = client.getForEntity("/hotelSchedule/2023", HotelScheduleResponseDto.class);

        assertNull(response);
    }


}