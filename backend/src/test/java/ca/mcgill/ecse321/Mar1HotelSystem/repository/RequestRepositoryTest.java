package ca.mcgill.ecse321.Mar1HotelSystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * This is the test class for the request repository.
 *
 * @author ZiXu Liu
 */
@SpringBootTest
public class RequestRepositoryTest {
    // Setting up the service repository
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private GeneralUserRepository userRepository;
    @Autowired
    private CustomHoursRepository customHoursRepository;
    @Autowired
    private OperatingHoursRepository operatingHoursRepository;
    @Autowired
    private HotelScheduleRepository hotelScheduleRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RequestRepository requestRepository;


    // Clearing the database after the test
    @AfterEach
    public void clearDatabase() {
        /*
        Deletion order: from parent to child

        request -> booking -> room -> hotel -> hotel schedule -> custom hours
                          \-> user                           \-> operating hours
                          \-> payment
         */
        requestRepository.deleteAll();
        bookingRepository.deleteAll();
        paymentRepository.deleteAll();
        roomRepository.deleteAll();
        hotelRepository.deleteAll();
        hotelScheduleRepository.deleteAll();
        employeeRepository.deleteAll();
        customHoursRepository.deleteAll();
        operatingHoursRepository.deleteAll();
        userRepository.deleteAll();
    }

    // Main test for the request repository
    @Test
    public void testPersistAndReadRequest() {
        // Creating an employee
        String firstName = "Just";
        String lastName = "Pi";
        String email = "pi@gmail.com";
        int phoneNumber = 439;
        String password = "smtidk";
        int hoursWorked = 8;
        Employee employee = new Employee(firstName, lastName, email, phoneNumber, password, hoursWorked);

        // Adding to the database
        employeeRepository.save(employee);

        // Creating a user
        String firstNameUser = "Alex";
        String lastNameUser = "Boi";
        String emailUser = "alex@email.com";
        int phoneNumberUser = 124;
        GeneralUser user = new GeneralUser(firstNameUser, lastNameUser, emailUser, phoneNumberUser);

        // Adding to the database
        userRepository.save(user);

        // Creating a new custom hour
        Date dateHour = new Date();
        int openingHour = 5;
        int closingHour = 6;
        CustomHours customHours = new CustomHours(dateHour, openingHour, closingHour);

        // Adding to the database
        customHoursRepository.save(customHours);

        // Creating a new operating hour
        OperatingHours.DayOfWeek dayOfWeek = OperatingHours.DayOfWeek.Tuesday;
        int openingHourOperating = 7;
        int closingHourOperating = 8;
        OperatingHours operatingHours = new OperatingHours(dayOfWeek, openingHourOperating, closingHourOperating);

        // Adding to the database
        operatingHoursRepository.save(operatingHours);

        // Creating a new hotel schedule
        int year = 2022;
        CustomHours[] customHoursList = {customHours};
        OperatingHours[] operatingHoursList = {operatingHours};
        HotelSchedule hotelSchedule = new HotelSchedule(year, operatingHoursList, customHoursList);

        // Adding to the database
        hotelScheduleRepository.save(hotelSchedule);

        // Creating a new hotel
        Hotel hotel = new Hotel(hotelSchedule);

        // Adding to the database
        hotelRepository.save(hotel);

        // Creating a new room
        Room.RoomType roomType = Room.RoomType.Regular;
        Room.BedType bedType = Room.BedType.Doubles;
        //boolean flagged = false;
        boolean isAvailable = true;
        int pricePerNight = 50;
        int maxCapacity = 1;
        Room room = new Room(roomType, bedType, isAvailable, pricePerNight, maxCapacity, hotel);

        // Adding to the database
        roomRepository.save(room);

        // Creating a new payment
        int amountPayment = 2;
        Payment payment = new Payment(amountPayment);

        // Adding to the database
        paymentRepository.save(payment);

        // Creating a new booking
        Booking booking = new Booking(payment, user, room);

        // Adding to the database
        bookingRepository.save(booking);

        // Creating a request
        String description = "Need some paper";
        boolean fulfilled = false;
        Request request = new Request(description, booking, fulfilled);

        // Adding to the database
        requestRepository.save(request);
        int id = request.getRequestId();

        // Assertions
        request = requestRepository.findRequestByRequestId(id);

        assertNotNull(request);
        assertEquals(description, request.getDescription());
        assertEquals(fulfilled, request.getIsFufilled());
        assertEquals(booking.getBookingId(), request.getBooking().getBookingId());

    }
}
