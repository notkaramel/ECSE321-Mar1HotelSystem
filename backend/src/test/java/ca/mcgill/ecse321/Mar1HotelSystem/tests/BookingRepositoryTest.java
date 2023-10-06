package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.*;

/**
 * This test class is for the BookingRepository CRUD against the database.
 * 
 * @author Bilar Mokhtari (@bmokhtari)
 * @author Antoine Phan (@notkaramel)
 * @author ZiXu Liu (@ARandomPi)
 */

@SpringBootTest
public class BookingRepositoryTest {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private GeneralUserRepository generalUserRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelScheduleRepository hotelScheduleRepository;

    @Autowired
    private CustomHoursRepository customHoursRepository;

    @Autowired
    private OperatingHoursRepository operatingHoursRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        /*
        Deletion order: from parent to child

        booking -> room -> hotel -> hotel schedule -> custom hours
                          \-> user                           \-> operating hours
                          \-> payment
         */
        bookingRepository.deleteAll();
        roomRepository.deleteAll();
        hotelRepository.deleteAll();
        paymentRepository.deleteAll();
        generalUserRepository.deleteAll();
        hotelScheduleRepository.deleteAll();
        customHoursRepository.deleteAll();
        operatingHoursRepository.deleteAll();
    }

    /**
     * This test is for the booking class
     * 
     * @author Mokhtari, Bilar
     */
    @Test
    public void testPersistAndLoadBooking() {
        // ---------------------------
        // Create Booking Dependencies
        // ---------------------------

        // Create Payment Object for Booking
        Payment payment = new Payment(500);
        paymentRepository.save(payment);

        // Create and Save User Object (Required for Booking)
        int phoneNumber = 438;
        GeneralUser user = new GeneralUser("John", "Wick", "johnwick@email.com", phoneNumber);
        generalUserRepository.save(user);

        // Create and Save CustomHours and OperatingHours Objects 
        Date date = new Date();
        CustomHours customHours = new CustomHours(date, 8, 20);
        OperatingHours operatingHours = new OperatingHours(DayOfWeek.Monday, 8, 20);

        CustomHours[] customHoursArray = new CustomHours[1];
        OperatingHours[] operatingHoursArray = new OperatingHours[1];
        customHoursRepository.save(customHours);
        operatingHoursRepository.save(operatingHours);

        customHoursArray[0] = customHours;
        operatingHoursArray[0] = operatingHours;

        // Create and Save HotelSchedule and Hotel Object
        HotelSchedule hotelSchedule = hotelScheduleRepository.findHotelScheduleByYear(2023);
        hotelSchedule = new HotelSchedule(2023, operatingHoursArray, customHoursArray);
        hotelScheduleRepository.save(hotelSchedule);
        Hotel hotel = new Hotel(hotelSchedule);

        // Saving hotel
        hotelRepository.save(hotel);

        // Create and Save Room Object (Required for Booking)
        Room room = new Room(RoomType.Suite, BedType.King, true, phoneNumber, phoneNumber, hotel);
        roomRepository.save(room);

        // ------------------
        // Create and Save Booking
        // ------------------
        Booking booking = new Booking(payment, user, room);
        bookingRepository.save(booking);
        int bookingId = booking.getBookingId();

        // Reading the booking
        booking = bookingRepository.findBookingByBookingId(bookingId);

        // ------------------
        // Assertions
        // ------------------
        assertNotNull(booking);
        assertEquals(payment.getAmount(), booking.getPayment().getAmount());
        assertEquals(user.getEmail(), booking.getGeneralUser().getEmail());
        assertEquals(room.getRoomId(), booking.getRoom().getRoomId());
    }
}
