package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.CustomHoursRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelScheduleRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.OperatingHoursRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;

import java.util.GregorianCalendar;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {
    
    @Mock
    private HotelScheduleRepository hotelScheduleDao;

    @Mock
    private CustomHoursRepository customHoursDao;

    @Mock
    private OperatingHoursRepository operatingHoursDao;

    @InjectMocks
    private ScheduleService scheduleService;
    

    private static final int YEAR_KEY = 2023;

    private static final DayOfWeek DAY_KEY = DayOfWeek.Friday;
    
    Date date = new GregorianCalendar(2023, Calendar.FEBRUARY, 20).getTime();

    private static final int OPENING_HOUR_KEY = 8;
    private static final int CLOSING_HOUR_KEY = 20;

    @BeforeEach
    public void setMockOutput() {
            lenient().when(hotelScheduleDao.findHotelScheduleByYear(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
                if(invocation.getArgument(0).equals(YEAR_KEY)){

                    OperatingHours operatingHour1 = scheduleService.createOperatingHours(DayOfWeek.Monday, 11, 12);
                    OperatingHours operatingHour2 = scheduleService.createOperatingHours(DayOfWeek.Tuesday, 11, 12);
                    OperatingHours[] operatingHoursArray = new OperatingHours[]{operatingHour1, operatingHour2};
                        
                    CustomHours customHours = scheduleService.createCustomHours(date, 11, 13);
                    CustomHours[] customHoursArray = new CustomHours[]{customHours};

                    HotelSchedule hotelSchedule = new HotelSchedule();
                    hotelSchedule = scheduleService.createHotelSchedule(YEAR_KEY, operatingHoursArray, customHoursArray);

                    return hotelSchedule;
                } else {
                    return null;
                }
            });
            lenient().when(customHoursDao.findCustomHoursByDate(any(Date.class))).thenAnswer( (InvocationOnMock invocation) -> {
                if(invocation.getArgument(0).equals(date)) {

                    int openingHour = 9; 
                    int closingHour = 14; 

                    CustomHours customHours = new CustomHours(date, openingHour, closingHour);
                    return customHours;
                } else {
                    return null;
                }
            });
            lenient().when(operatingHoursDao.findOperatingHoursByDay(any(DayOfWeek.class))).thenAnswer( (InvocationOnMock invocation) -> {
                if(invocation.getArgument(0).equals(DAY_KEY)) {
                    OperatingHours operatingHours = new OperatingHours(DAY_KEY, OPENING_HOUR_KEY, CLOSING_HOUR_KEY);
                    return operatingHours;
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
        int openingHour = 8; //8:00am
        int closingHour = 21; //9:00pm

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
    public void testUpdateOperatingHours() {

        OperatingHours initialOperatingHours = null;
        OperatingHours updatedOperatingHours = null;

        DayOfWeek day = DayOfWeek.Friday;
        int initialOpeningHour = 8; //8:00am
        int initialClosingHour = 21; //9:00pm

        int updatedOpeningHour = 9; //8:00am
        int updatedClosingHour = 22; //9:00pm

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
    public void testGetOperatingHoursByDay() {

        OperatingHours operatingHours = null;
        OperatingHours retrievedHours = null;

        DayOfWeek day = DayOfWeek.Friday;
        int openingHour = 8; //8:00am
        int closingHour = 20; //9:00pm

        try {
            retrievedHours = scheduleService.getOperatingHoursByDay(day);
            
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(retrievedHours);
        assertEquals(openingHour, retrievedHours.getOpeningHour());
        assertEquals(closingHour, retrievedHours.getClosingHour());
    }

    @Test
    public void testGetAllOperatingHoursByDay() {

        OperatingHours operatingHoursS = null;
        OperatingHours operatingHoursM = null;
        OperatingHours operatingHoursT = null;
        OperatingHours operatingHoursW = null;

        List<OperatingHours> allOperatingHours = null;

        DayOfWeek dayS = DayOfWeek.Sunday;
        int openingHourS = 11; 
        int closingHourS = 17; 

        DayOfWeek dayM = DayOfWeek.Monday;
        int openingHourM = 10; 
        int closingHourM = 18; 

        DayOfWeek dayT = DayOfWeek.Tuesday;
        int openingHourT = 9; 
        int closingHourT = 19; 

        DayOfWeek dayW = DayOfWeek.Wednesday;
        int openingHourW = 8; 
        int closingHourW = 20; 

        try {
            operatingHoursS = scheduleService.createOperatingHours(dayS, openingHourS, closingHourS);
            operatingHoursM = scheduleService.createOperatingHours(dayM, openingHourM, closingHourM);
            operatingHoursT = scheduleService.createOperatingHours(dayT, openingHourT, closingHourT);
            operatingHoursW = scheduleService.createOperatingHours(dayW, openingHourW, closingHourW);
            allOperatingHours = scheduleService.getAllOperatingHours();

        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(allOperatingHours);

    }


    // CUSTOM HOURS
    @Test
    public void testCreateCustomHours() {

        CustomHours customHours = null;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();

        int openingHour = 11; 
        int closingHour = 12; 

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
    public void testUpdateCustomHours() {

        CustomHours initialCustomHours = null;
        CustomHours updatedCustomHours = null;

        int initialOpeningHour = 8; //8:00am
        int initialClosingHour = 21; //9:00pm

        int updatedOpeningHour = 9; //8:00am
        int updatedClosingHour = 22; //9:00pm

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
    public void testGetCustomHoursByDate() {

        CustomHours retrievedHours = null;

        int openingHour = 9; 
        int closingHour = 14;

        try {
            retrievedHours = scheduleService.getCustomHoursByDate(date);
            
        } catch (IllegalArgumentException e) {
            fail();
        };

        assertNotNull(retrievedHours);
        assertEquals(date, retrievedHours.getDate());
        assertEquals(openingHour, retrievedHours.getOpeningHour());
        assertEquals(closingHour, retrievedHours.getClosingHour());
    }

    @Test
    public void testDeleteCustomHours() {
        
        CustomHours customHours = null;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();

        int openingHour = 11; 
        int closingHour = 12; 

        try {
            customHours = scheduleService.createCustomHours(date, openingHour, closingHour);
            scheduleService.deleteCustomHours(date);
            
        } catch (IllegalArgumentException e) {
            fail();
        };

        assertNull(scheduleService.getCustomHoursByDate(date));
    }

    @Test
    public void testGetAllCustomHours() {
        
        CustomHours customHours1 = null;
        CustomHours customHours2 = null;
        CustomHours customHours3 = null;
        List<CustomHours> allCustomHours = null;

        Calendar calendar = Calendar.getInstance();

        calendar.set(2001, Calendar.FEBRUARY, 20);
        Date date1 = calendar.getTime();
        int openingHour1 = 11; 
        int closingHour1 = 12;

        calendar.set(2001, Calendar.FEBRUARY, 21);
        Date date2 = calendar.getTime();
        int openingHour2 = 10; 
        int closingHour2 = 13;

        calendar.set(2001, Calendar.FEBRUARY, 22);
        Date date3 = calendar.getTime();
        int openingHour3 = 9; 
        int closingHour3 = 14; 

        try {
            customHours1   = scheduleService.createCustomHours(date1, openingHour1, closingHour1);
            customHours2   = scheduleService.createCustomHours(date2, openingHour2, closingHour2);
            customHours3   = scheduleService.createCustomHours(date3, openingHour3, closingHour3);
            allCustomHours = scheduleService.getAllCustomHours();

        } catch (IllegalArgumentException e) {
            fail();
        };

        assertNotNull(allCustomHours);
    }

    // HOTEL SCHEDULE
    @Test
    public void testCreateHotelHours() {

        OperatingHours operatingHour1 = scheduleService.createOperatingHours(DayOfWeek.Monday, 11, 12);
        OperatingHours operatingHour2 = scheduleService.createOperatingHours(DayOfWeek.Tuesday, 11, 12);
        OperatingHours[] operatingHoursArray = new OperatingHours[]{operatingHour1, operatingHour2};
                        
        CustomHours customHours = scheduleService.createCustomHours(date, 11, 13);
        CustomHours[] customHoursArray = new CustomHours[]{customHours};

        HotelSchedule hotelSchedule = scheduleService.createHotelSchedule(YEAR_KEY, operatingHoursArray, customHoursArray);

        try {
            hotelSchedule = scheduleService.createHotelSchedule(YEAR_KEY, operatingHoursArray, customHoursArray);
            
        } catch (IllegalArgumentException e) {
            fail();
        };
        assertNotNull(hotelSchedule);
    }

    @Test
    public void testGetHotelScheduleByYear() {
        HotelSchedule retrievedHotelSchedule = null;

        try {
            retrievedHotelSchedule = scheduleService.getHotelScheduleByYear(YEAR_KEY);
            
        } catch (IllegalArgumentException e) {
            fail();
        };

        assertNotNull(retrievedHotelSchedule);
    }

    @Test
    public void testGetAllHotelSchedule() {

        List<HotelSchedule> retrievedHotelSchedule = null;

        try {
            
            retrievedHotelSchedule = scheduleService.getAllHotelSchedules();
            
        } catch (IllegalArgumentException e) {
            fail();
        };
        assertNotNull(retrievedHotelSchedule);
    }

    public void testDeleteHotelHours() {

        HotelSchedule hotelSchedule = null;

        DayOfWeek day1 = DayOfWeek.Monday;
        int openingHour1 = 11; 
        int closingHour1 = 12;

        DayOfWeek day2 = DayOfWeek.Monday;
        int openingHour2 = 10; 
        int closingHour2 = 13;

        OperatingHours operatingHour1 = scheduleService.createOperatingHours(day1, openingHour1, closingHour1);
        OperatingHours operatingHour2 = scheduleService.createOperatingHours(day2, openingHour2, closingHour2);

        OperatingHours[] operatingHoursArray = new OperatingHours[]{operatingHour1, operatingHour2};
        
        int year = 2023;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 20);
        Date date = calendar.getTime();

        int openingHour3 = 9; 
        int closingHour3 = 14; 
        
        CustomHours customHours = scheduleService.createCustomHours(date, openingHour3, closingHour3);

        CustomHours[] customHoursArray = new CustomHours[]{customHours};

        try {
            hotelSchedule = scheduleService.createHotelSchedule(year, operatingHoursArray, customHoursArray);
            scheduleService.deleteHotelSchedule(year);
            
        } catch (IllegalArgumentException e) {
            fail();
        };
        assertNull(hotelSchedule);
    }
    
}
