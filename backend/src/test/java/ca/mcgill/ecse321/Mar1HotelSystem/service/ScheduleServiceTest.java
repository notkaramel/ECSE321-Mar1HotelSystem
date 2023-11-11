package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.CustomHoursRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelScheduleRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.OperatingHoursRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.HotelScheduleRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;

import java.util.GregorianCalendar;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.lenient;

/**
 * Schedule Service Tests
 * 
 * @author Emma Friesen (@emma-friesen)
 */
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

    Date DATE = new GregorianCalendar(2023, Calendar.FEBRUARY, 20).getTime();

    // unique Id for objects
    private static final int YEAR_KEY = 2023;
    private static final int OH_ID1 = 16;
    private static final int OH_ID2 = 25;
    private static final int CH_ID = 46;

    @BeforeEach
    public void setMockOutput() {
        OperatingHours operatingHour1 = scheduleService.createOperatingHours(DayOfWeek.Monday, 8, 16);
        operatingHour1.setOpeningHoursId(OH_ID1);
        OperatingHours operatingHour2 = scheduleService.createOperatingHours(DayOfWeek.Tuesday, 8, 16);
        operatingHour2.setOpeningHoursId(OH_ID2);

        CustomHours customHours = scheduleService.createCustomHours(DATE, 11, 13);
        customHours.setCustomHoursId(CH_ID);

        List<OperatingHours> operatingHoursArray = Arrays.asList(operatingHour1, operatingHour2);
        List<CustomHours> customHoursArray = Arrays.asList(customHours);

        HotelSchedule hotelSchedule = new HotelSchedule();
        hotelSchedule.setYear(YEAR_KEY);
        hotelSchedule.setOperatingHours(operatingHoursArray);
        hotelSchedule.setCustomHours(customHoursArray);

        // MOCK Finding methods
        lenient().when(hotelScheduleDao.findHotelScheduleByYear(YEAR_KEY)).thenReturn(hotelSchedule);

        lenient().when(customHoursDao.findCustomHoursByDate(isA(Date.class)))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(DATE)) {
                        return customHours;
                    } else {
                        return null;
                    }
                });

        lenient().when(operatingHoursDao.findOperatingHoursByDay(isA(DayOfWeek.class)))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(DayOfWeek.Monday)) {
                        return operatingHour1;
                    } else if (invocation.getArgument(0).equals(DayOfWeek.Tuesday)) {
                        return operatingHour2;
                    } else {
                        return null;
                    }
                });

        lenient().when(operatingHoursDao.findOperatingHoursByOperatingHoursId(anyInt())).thenAnswer(
                (InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(OH_ID1)) {
                        return operatingHour1;
                    } else if (invocation.getArgument(0).equals(OH_ID2)) {
                        return operatingHour2;
                    } else {
                        return null;
                    }
                });

        lenient().when(customHoursDao.findCustomHoursByCustomHoursId(CH_ID)).thenReturn(customHours);

        // Mock save methods
        lenient().when(hotelScheduleDao.save(any(HotelSchedule.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });

        lenient().when(operatingHoursDao.save(any(OperatingHours.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });

        lenient().when(customHoursDao.save(any(CustomHours.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });
    }

    // OPERATING HOURS
    @Test
    public void testCreateOperatingHours() {
        DayOfWeek day = DayOfWeek.Friday;
        int openingHour = 8; // 8:00am
        int closingHour = 21; // 9:00pm

        try {
            OperatingHours operatingHours = scheduleService.createOperatingHours(day, openingHour, closingHour);

            assertNotNull(operatingHours);
            assertEquals(day, operatingHours.getDayOfWeek());
            assertEquals(openingHour, operatingHours.getOpeningHour());
            assertEquals(closingHour, operatingHours.getClosingHour());
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void testUpdateOperatingHours() {
        DayOfWeek day = DayOfWeek.Monday;

        int updatedOpeningHour = 9; // 8:00am
        int updatedClosingHour = 22; // 9:00pm

        try {
            OperatingHours updatedOperatingHours = scheduleService.updateOperatingHoursByDay(day, updatedOpeningHour,
                    updatedClosingHour);

            assertNotNull(updatedOperatingHours);
            assertEquals(day, updatedOperatingHours.getDayOfWeek());
            assertEquals(updatedOpeningHour, updatedOperatingHours.getOpeningHour());
            assertEquals(updatedClosingHour, updatedOperatingHours.getClosingHour());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testGetOperatingHoursByDay() {
        try {
            OperatingHours oh = scheduleService.getOperatingHoursByDay(DayOfWeek.Monday);
            // Should return Mock operatingHours1
            assertNotNull(oh);
            assertEquals(DayOfWeek.Monday, oh.getDayOfWeek());
            assertEquals(8, oh.getOpeningHour());
            assertEquals(16, oh.getClosingHour());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testGetOperatingHoursById() {
        try {
            OperatingHours oh = scheduleService.getOperatingHoursById(OH_ID1);
            // Should return Mock operatingHours1
            assertNotNull(oh);
            assertEquals(DayOfWeek.Monday, oh.getDayOfWeek());
            assertEquals(8, oh.getOpeningHour());
            assertEquals(16, oh.getClosingHour());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testGetAllOperatingHoursByDay() {
        lenient().when(operatingHoursDao.save(isA(OperatingHours.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });

        try {
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

            OperatingHours operatingHoursS = scheduleService.createOperatingHours(dayS, openingHourS, closingHourS);
            OperatingHours operatingHoursM = scheduleService.createOperatingHours(dayM, openingHourM, closingHourM);
            OperatingHours operatingHoursT = scheduleService.createOperatingHours(dayT, openingHourT, closingHourT);
            OperatingHours operatingHoursW = scheduleService.createOperatingHours(dayW, openingHourW, closingHourW);

            lenient().when(operatingHoursDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
                return Arrays.asList(operatingHoursS, operatingHoursM, operatingHoursT, operatingHoursW);
            });

            List<OperatingHours> allOperatingHours = scheduleService.getAllOperatingHours();
            assertNotNull(allOperatingHours);
            assertEquals(4, allOperatingHours.size());
        } catch (Exception e) {
            fail();
        }
    }

    // CUSTOM HOURS
    @Test
    public void testCreateCustomHours() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2001, Calendar.FEBRUARY, 20);
            Date date = calendar.getTime();

            int openingHour = 11;
            int closingHour = 12;

            CustomHours customHours = scheduleService.createCustomHours(date, openingHour, closingHour);

            assertNotNull(customHours);
            assertEquals(date, customHours.getDate());
            assertEquals(openingHour, customHours.getOpeningHour());
            assertEquals(closingHour, customHours.getClosingHour());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testUpdateCustomHours() {
        try {
            int initialOpeningHour = 8; // 8:00am
            int initialClosingHour = 21; // 9:00pm

            int updatedOpeningHour = 9; // 8:00am
            int updatedClosingHour = 22; // 9:00pm
            CustomHours initialCustomHours = scheduleService.createCustomHours(DATE, initialOpeningHour,
                    initialClosingHour);
            assertNotNull(initialCustomHours);

            CustomHours updatedCustomHours = scheduleService.updateCustomHoursByDate(DATE, updatedOpeningHour,
                    updatedClosingHour);
            assertNotNull(updatedCustomHours);
            assertEquals(DATE, updatedCustomHours.getDate());
            assertEquals(updatedOpeningHour, updatedCustomHours.getOpeningHour());
            assertEquals(updatedClosingHour, updatedCustomHours.getClosingHour());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testGetCustomHoursByDate() {
        try {
            CustomHours retrievedHours = scheduleService.getCustomHoursByDate(DATE);
            assertNotNull(retrievedHours);
            assertEquals(DATE, retrievedHours.getDate());
            assertEquals(11, retrievedHours.getOpeningHour());
            assertEquals(13, retrievedHours.getClosingHour());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testGetCustomHoursById() {
        try {
            CustomHours retrievedHours = scheduleService.getCustomHoursById(CH_ID);
            assertNotNull(retrievedHours);
            assertEquals(DATE, retrievedHours.getDate());
            assertEquals(11, retrievedHours.getOpeningHour());
            assertEquals(13, retrievedHours.getClosingHour());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testUpdateCustomHoursById() {
        try {
            int initialOpeningHour = 8; // 8:00am
            int initialClosingHour = 21; // 9:00pm

            int updatedOpeningHour = 9; // 8:00am
            int updatedClosingHour = 22; // 9:00pm
            CustomHours updatedCustomHours = scheduleService.updateCustomHoursById(CH_ID, updatedOpeningHour,
                    updatedClosingHour);
            assertNotNull(updatedCustomHours);
            assertEquals(DATE, updatedCustomHours.getDate());

            assertNotEquals(initialOpeningHour, updatedCustomHours.getOpeningHour());
            assertNotEquals(initialClosingHour, updatedCustomHours.getClosingHour());

            assertEquals(updatedOpeningHour, updatedCustomHours.getOpeningHour());
            assertEquals(updatedClosingHour, updatedCustomHours.getClosingHour());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testDeleteCustomHours() {
        try {
            CustomHours deletedCustomHours = scheduleService.deleteCustomHoursByDate(DATE);
            assertNotNull(deletedCustomHours);
            assertEquals(CH_ID, deletedCustomHours.getCustomHoursId());
        } catch (IllegalArgumentException e) {
            fail();
        }

    }

    @Test
    public void testGetAllCustomHours() {
        try {
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

            CustomHours customHours1 = scheduleService.createCustomHours(date1, openingHour1, closingHour1);
            CustomHours customHours2 = scheduleService.createCustomHours(date2, openingHour2, closingHour2);
            CustomHours customHours3 = scheduleService.createCustomHours(date3, openingHour3, closingHour3);
            lenient().when(customHoursDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
                return Arrays.asList(customHours1, customHours2, customHours3);
            });
            List<CustomHours> allCustomHours = scheduleService.getAllCustomHours();

            assertNotNull(allCustomHours);
        } catch (IllegalArgumentException e) {
            fail();
        }

    }

    // HOTEL SCHEDULE
    @Test
    public void testCreateHotelHours() {
        int[] operatingHoursIds = { OH_ID1, OH_ID2 };
        int[] customHoursIds = { CH_ID };
        HotelScheduleRequestDto hotelScheduleRequestDto = new HotelScheduleRequestDto(YEAR_KEY, operatingHoursIds,
                customHoursIds);
        try {
            HotelSchedule hotelSchedule = scheduleService.createHotelSchedule(hotelScheduleRequestDto);
            assertNotNull(hotelSchedule);
            assertEquals(YEAR_KEY, hotelSchedule.getYear());
            assertEquals(2, hotelSchedule.getOperatingHours().size());
            assertEquals(1, hotelSchedule.getCustomHours().size());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testGetHotelScheduleByYear() {
        try {
            HotelSchedule retrievedHotelSchedule = scheduleService.getHotelScheduleByYear(YEAR_KEY);
            assertNotNull(retrievedHotelSchedule);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testGetHotelScheduleByYearInvalid() {
        Mar1HotelSystemException error = null;
        try {
            scheduleService.getHotelScheduleByYear(YEAR_KEY + 1);
            fail();
        } catch (Mar1HotelSystemException e) {
            error = e;
        }
        assertNotNull(error);
        assertEquals(HttpStatus.NOT_FOUND, error.getStatus());
        assertEquals("Could not find HotelSchedule of year " + (YEAR_KEY + 1) + ".", error.getMessage());
    }

    @Test
    public void testGetAllHotelSchedule() {
        try {
            lenient().when(hotelScheduleDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
                return Arrays.asList(new HotelSchedule(2023, null, null));
            });

            List<HotelSchedule> retrievedHotelSchedule = scheduleService.getAllHotelSchedules();
            assertNotNull(retrievedHotelSchedule);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testDeleteHotelHours() {
        try {
            HotelSchedule hs = scheduleService.deleteHotelSchedule(YEAR_KEY);
            assertEquals(hs, scheduleService.getHotelScheduleByYear(YEAR_KEY));
        } catch (IllegalArgumentException e) {
            fail();
        }
    }
}
