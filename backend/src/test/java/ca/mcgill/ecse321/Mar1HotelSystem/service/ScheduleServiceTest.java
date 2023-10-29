package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.CustomHoursRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelScheduleRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.OperatingHoursRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.TimeZoneStorage;
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {
    
    @Mock
    private HotelScheduleRepository hotelScheduleDao;
    private CustomHoursRepository customHoursDao;

    @InjectMocks
    private ScheduleService scheduleService;
    
    private static final Integer YEAR_KEY = 2023;

    @BeforeEach
    public void setMockOutput() {
            lenient().when(hotelScheduleDao.findHotelScheduleByYear(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
                if(invocation.getArgument(0).equals(YEAR_KEY)) {
                    HotelSchedule hotelSchedule = new HotelSchedule();
                    hotelSchedule.setYear(YEAR_KEY);
                    return hotelSchedule;
                } else {
                    return null;
                }
            });
    }
    
    // OPERATING HOURS
    @Test
    public void testCreateOperatingHours() {

        OperatingHours operatingHours = null;

        DayOfWeek day = DayOfWeek.Friday;
        Integer openingHour = 8; //8:00am
        Integer closingHour = 21; //9:00pm

        try {
            operatingHours = scheduleService.createOperatingHours(day, openingHour, closingHour);
            
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(operatingHours);
        assertEquals(day, operatingHours.getDayOfWeek());
        assertEquals(openingHour, operatingHours.getOpeningHour());
        assertEquals(closingHour, operatingHours.getClosingHour());
    }

    @Test
    public void testCreateOperatingHoursEmpty() {
        String error = null;
        OperatingHours operatingHours = null;

        DayOfWeek day = null;
        Integer openingHour = null; 
        Integer closingHour = null; 

        try {
            operatingHours = scheduleService.createOperatingHours(day, openingHour, closingHour);
            
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        
        assertNull(operatingHours);
    }

    @Test
    public void testUpdateOperatingHours() {

        OperatingHours initialOperatingHours = null;
        OperatingHours updatedOperatingHours = null;

        DayOfWeek day = DayOfWeek.Friday;
        Integer initialOpeningHour = 8; //8:00am
        Integer initialClosingHour = 21; //9:00pm

        Integer updatedOpeningHour = 9; //8:00am
        Integer updatedClosingHour = 22; //9:00pm

        try {
            initialOperatingHours = scheduleService.createOperatingHours(day, initialOpeningHour, initialClosingHour);
            updatedOperatingHours = scheduleService.updateOperatingHours(day, updatedOpeningHour, updatedClosingHour);
            
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(updatedOperatingHours);
        assertEquals(day, updatedOperatingHours.getDayOfWeek());
        assertEquals(updatedOpeningHour, updatedOperatingHours.getOpeningHour());
        assertEquals(updatedClosingHour, updatedOperatingHours.getClosingHour());
    }

    @Test
    public void testUpdateOperatingHoursEmpty() {
        String error = null;
        OperatingHours initialOperatingHours = null;
        OperatingHours updatedOperatingHours = null;

        DayOfWeek day = DayOfWeek.Friday;
        Integer initialOpeningHour = 8;
        Integer initialClosingHour = 21; 

        Integer updatedOpeningHour = null;
        Integer updatedClosingHour = null; 

        try {
            initialOperatingHours = scheduleService.createOperatingHours(day, initialOpeningHour, initialClosingHour);
            updatedOperatingHours = scheduleService.updateOperatingHours(day, updatedOpeningHour, updatedClosingHour);
            
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(updatedOperatingHours);
    }

    @Test
    public void testGetOperatingHoursByDay() {

        OperatingHours operatingHours = null;

        DayOfWeek day = DayOfWeek.Friday;
        Integer openingHour = 8; //8:00am
        Integer closingHour = 21; //9:00pm

        try {
            operatingHours = scheduleService.createOperatingHours(day, openingHour, closingHour);
            operatingHours = scheduleService.getOperatingHoursByDay(day);
            
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(operatingHours);
        assertEquals(openingHour, operatingHours.getOpeningHour());
        assertEquals(closingHour, operatingHours.getClosingHour());
    }

    @Test
    public void testGetOperatingHoursEmpty() {

        OperatingHours operatingHours = null;

        DayOfWeek day = null;
        Integer openingHour = null; 
        Integer closingHour = null; 

        try {
            operatingHours = scheduleService.createOperatingHours(day, openingHour, closingHour);
            operatingHours = scheduleService.getOperatingHoursByDay(day);
            
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNull(operatingHours);
    }


    // CUSTOM HOURS
    @Test
    public void testCreateCustomHours() {

        CustomHours customHours = null;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();

        Integer openingHour = 11; 
        Integer closingHour = 12; 

        try {
            customHours = scheduleService.createCustomHours(date, openingHour, closingHour);
            
        } catch (IllegalArgumentException e) {
            fail();
        };
        assertNotNull(customHours);
        assertEquals(date, customHours.getDate());
        assertEquals(openingHour, customHours.getOpeningHour());
        assertEquals(closingHour, customHours.getClosingHour());
    }

    @Test
    public void testCreateCustomHoursEmpty() {
        String error = null;
        CustomHours customHours = null;

        Date date = null;
        Integer openingHour = null; 
        Integer closingHour = null; 

        try {
            customHours = scheduleService.createCustomHours(date, openingHour, closingHour);
            
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        };
        assertNull(customHours);
    }
    
    @Test
    public void testUpdateCustomHours() {

        CustomHours initialCustomHours = null;
        CustomHours updatedCustomHours = null;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();

        Integer initialOpeningHour = 8; //8:00am
        Integer initialClosingHour = 21; //9:00pm

        Integer updatedOpeningHour = 9; //8:00am
        Integer updatedClosingHour = 22; //9:00pm


        try {
            initialCustomHours = scheduleService.createCustomHours(date, initialOpeningHour, initialClosingHour);
            updatedCustomHours = scheduleService.updateCustomHours(date, updatedOpeningHour, updatedClosingHour);
            
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(updatedCustomHours);
        assertEquals(date, updatedCustomHours.getDate());
        assertEquals(updatedOpeningHour, updatedCustomHours.getOpeningHour());
        assertEquals(updatedClosingHour, updatedCustomHours.getClosingHour());
    }

    @Test
    public void testUpdateCustomHoursEmpty() {
        String error = null;
        CustomHours initialCustomHours = null;
        CustomHours updatedCustomHours = null;


        Date date = null;

        Integer initialOpeningHour = 8; 
        Integer initialClosingHour = 21;
        Integer updatedOpeningHour = null; 
        Integer updatedClosingHour = null; 


        try {
            initialCustomHours = scheduleService.createCustomHours(date, initialOpeningHour, initialClosingHour);
            updatedCustomHours = scheduleService.updateCustomHours(date, updatedOpeningHour, updatedClosingHour);
            
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(updatedCustomHours);
    }

    @Test
    public void testGetCustomHoursByDate() {
        CustomHours customHours = null;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();

        Integer openingHour = 11; 
        Integer closingHour = 12; 

        try {
            customHours = scheduleService.createCustomHours(date, openingHour, closingHour);
            customHours = scheduleService.getCustomHoursByDate(date);
            
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        };
        assertNull(customHours);
    }

    @Test
    public void testGetCustomHoursByDateEmpty() {
        String error = null;
        CustomHours customHours = null;

        Date date = null;
        Integer openingHour = null; 
        Integer closingHour = null; 

        try {
            customHours = scheduleService.createCustomHours(date, openingHour, closingHour);
            customHours = scheduleService.getCustomHoursByDate(date);
            
        } catch (IllegalArgumentException e) {
            fail();
        };
        assertNotNull(customHours);
        assertEquals(openingHour, customHours.getOpeningHour());
        assertEquals(closingHour, customHours.getClosingHour());
    }

    @Test
    public void testDeleteCustomHours() {
        
        CustomHours customHours = null;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();

        Integer openingHour = 11; 
        Integer closingHour = 12; 

        try {
            customHours = scheduleService.createCustomHours(date, openingHour, closingHour);
            scheduleService.deleteCustomHours(date);
            
        } catch (IllegalArgumentException e) {
            fail();
        };
        assertNull(scheduleService.getCustomHoursByDate(date));
    }

    // HOTEL SCHEDULE
    
}
