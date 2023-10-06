package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

// import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.User;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.BookingRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.UserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.User;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelRepository;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        bookingRepository.deleteAll();
        paymentRepository.deleteAll();
        userRepository.deleteAll();
        roomRepository.deleteAll();
    }
/**
 * This test is for the booking class
 * @author Mokhtari, Bilar
 * 
 */
    @Test
    public void testPersistAndLoadBooking(){
        // ---------------------------
        // Create Booking Dependencies
        // ---------------------------

        // Create Payment Object for Booking
        int bookingId = 1;
        Payment payment = new Payment(500, 0);

        // Create and Save User Object (Required for Booking)
        int phoneNumber = 438;
        User user = new User("John","Wick","johnwick@email.com",phoneNumber);
        user = userRepository.save(user);

        // Create and Save Hotel Object (Required for Room, which is in turn required for Booking)
        Date date = new Date();
        CustomHours customHours = new CustomHours(date, 8, 20);
        OperatingHours operatingHours = new OperatingHours(DayOfWeek.Monday, 8, 20);
        CustomHours[] customHoursArray = new CustomHours[1];
        OperatingHours[] operatingHoursArray = new OperatingHours[1];
        customHoursArray[0] = customHours;
        operatingHoursArray[0] = operatingHours;
        HotelSchedule hotelSchedule = new HotelSchedule(2023, operatingHoursArray, customHoursArray);
        Hotel hotel = new Hotel(hotelSchedule);

        //Saving hotel
        hotel = hotelRepository.save(hotel);

        // Create and Save Room Object (Required for Booking)
        Room room = new Room(RoomType.Suite, BedType.King, true, phoneNumber, phoneNumber, null, bookingId);
        room = roomRepository.save(room);

        // ------------------
        // Create and Save Booking
        // ------------------
        Booking booking = new Booking(bookingId, payment, user, room);
        booking = bookingRepository.save(booking);

        //Reading the booking
        booking = bookingRepository.findBookingByBookingID(bookingId);

        // ------------------
        // Assertions
        // ------------------
        assertNotNull(booking);
        assertEquals(payment,booking.getPayment());
        assertEquals(user,booking.getUser());
        assertEquals(room,booking.getRoom());






    }
}
