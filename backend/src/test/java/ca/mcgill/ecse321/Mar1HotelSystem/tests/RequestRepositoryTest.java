package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

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
        employeeRepository.deleteAll();
        customHoursRepository.deleteAll();
        customHoursRepository.deleteAll();
        operatingHoursRepository.deleteAll();
        hotelScheduleRepository.deleteAll();
        hotelRepository.deleteAll();
        roomRepository.deleteAll();
        paymentRepository.deleteAll();;
        bookingRepository.deleteAll();
        requestRepository.deleteAll();
    }

    @Test
    public void testPersistAndReadRequests () {
        // Creating an employee
        String firstName = "Just";
        String lastName = "Pi";
        String email = "pi@gmail.com";
        int phoneNumber = 439;
        String password = "smtidk";
        int hoursWorked = 8;
        Employee employee = new Employee(firstName, lastName, email, phoneNumber, password, hoursWorked);

        employeeRepository.save(employee);

        // Creating a user
        String firstNameUser = "Alex";
        String lastNameUser = "Boi";
        String emailUser = "alex@email.com";
        int phoneNumberUser = 124;
        GeneralUser user = new GeneralUser(firstNameUser, lastNameUser, emailUser, phoneNumberUser);

        userRepository.save(user);

        // Creating a new custom hour
        Date dateHour = new Date();
        int openingHour = 5;
        int closingHour = 6;
        CustomHours customHours = new CustomHours(dateHour, openingHour, closingHour);

        customHoursRepository.save(customHours);

        // Creating a new operating hour
        OperatingHours.DayOfWeek dayOfWeek = OperatingHours.DayOfWeek.Tuesday;
        int openingHourOperating = 7;
        int closingHourOperating = 8;
        OperatingHours operatingHours = new OperatingHours(dayOfWeek, openingHourOperating, closingHourOperating);

        operatingHoursRepository.save(operatingHours);

        // Creating a new hotel schedule
        int year = 2022;
        CustomHours[] customHoursList = {customHours};
        OperatingHours[] operatingHoursList = {operatingHours};
        HotelSchedule hotelSchedule = new HotelSchedule(year, operatingHoursList, customHoursList);

        hotelScheduleRepository.save(hotelSchedule);

        // Creating a new hotel
        Hotel hotel = new Hotel(hotelSchedule);

        hotelRepository.save(hotel);

        // Creating a new room
        Room.RoomType roomType = Room.RoomType.Regular;
        Room.BedType bedType = Room.BedType.Doubles;
        //boolean flagged = false;
        boolean isAvailable = true;
        int pricePerNight = 50;
        int maxCapacity = 1;
        Room room = new Room(roomType, bedType, isAvailable, pricePerNight, maxCapacity, hotel);

        roomRepository.save(room);

        // Creating a new payment
        int amountPayment = 2;
        Payment payment = new Payment(amountPayment);

        paymentRepository.save(payment);

        // Creating a new booking
        Booking booking = new Booking(payment, user, room);

        bookingRepository.save(booking);

        // Creating a request
        String description = "Need some paper";
        boolean fulfilled = false;
        Request request = new Request(description, booking, fulfilled);

        requestRepository.save(request);
        int id = request.getRequestId();

        // Assertions
        request = requestRepository.findRequestByRequestId(id);

        assertNotNull(request);
        assertEquals(description, request.getDescription());
        assertEquals(fulfilled, request.getIsFufilled());
        assertEquals(booking, request.getBooking());

    }
}
