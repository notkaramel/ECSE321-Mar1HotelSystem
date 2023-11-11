package ca.mcgill.ecse321.Mar1HotelSystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.*;
import ca.mcgill.ecse321.Mar1HotelSystem.model.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * This is the test class for the service repository.
 *
 * @author ZiXu Liu
 */
@SpringBootTest
public class AssignmentRepositoryTest {
    // Setting up the repositories
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
    @Autowired
    private AssignmentRepository assignmentRepository;


    // Clearing the database after the test
    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        /*
        Deletion order: from parent to child

        service -> request -> booking -> room -> hotel -> hotel schedule -> custom hours
               \-> employee          \-> user                           \-> operating hours
                                     \-> payment
         */
        assignmentRepository.deleteAll();
        requestRepository.deleteAll();
        bookingRepository.deleteAll();
        employeeRepository.deleteAll();
        paymentRepository.deleteAll();
        roomRepository.deleteAll();
        hotelRepository.deleteAll();
        hotelScheduleRepository.deleteAll();
        customHoursRepository.deleteAll();
        operatingHoursRepository.deleteAll();
        userRepository.deleteAll();
    }

    // Main test for the Assignment repository
    @Test
    public void testPersistAndReadAssignment() {
        // Creating an employee
        String firstName = "Candice";
        String lastName = "Evergreen";
        String email = "candice@gmail.com";
        int phoneNumber = 438;
        String password = "BOI100";
        int hoursWorked = 10;
        Employee employee = new Employee(firstName, lastName, email, phoneNumber, password, hoursWorked);

        // Adding to the database
        employeeRepository.save(employee);

        // Creating a user
        String firstNameUser = "Random";
        String lastNameUser = "Dude";
        String emailUser = "Random@email.com";
        int phoneNumberUser = 123;
        GeneralUser user = new GeneralUser(firstNameUser, lastNameUser, emailUser, phoneNumberUser);

        // Adding to the database
        userRepository.save(user);

        // Creating a new custom hour
        Date dateHour = new Date();
        int openingHour = 1;
        int closingHour = 2;
        CustomHours customHours = new CustomHours(dateHour, openingHour, closingHour);

        // Adding to the database
        customHoursRepository.save(customHours);

        // Creating a new operating hour
        OperatingHours.DayOfWeek dayOfWeek = OperatingHours.DayOfWeek.Monday;
        int openingHourOperating = 3;
        int closingHourOperating = 4;
        OperatingHours operatingHours = new OperatingHours(dayOfWeek, openingHourOperating, closingHourOperating);

        // Adding to the database
        operatingHoursRepository.save(operatingHours);

        // Creating a new hotel schedule
        int year = 2023;
        List<CustomHours> customHoursList = Arrays.asList(customHours);
        List<OperatingHours> operatingHoursList = Arrays.asList(operatingHours);
        HotelSchedule hotelSchedule = new HotelSchedule(year, operatingHoursList, customHoursList);

        // Adding to the database
        hotelScheduleRepository.save(hotelSchedule);

        // Creating a new hotel
        Hotel hotel = new Hotel(hotelSchedule);

        // Adding to the database
        hotelRepository.save(hotel);

        // Creating a new room
        Room.RoomType roomType = Room.RoomType.Deluxe;
        Room.BedType bedType = Room.BedType.King;
        //boolean flagged = false;
        boolean isAvailable = true;
        int pricePerNight = 5;
        int maxCapacity = 2;
        Room room = new Room(roomType, bedType, isAvailable, pricePerNight, maxCapacity, hotel);

        // Adding to the database
        roomRepository.save(room);

        // Creating a new payment
        int amountPayment = 50;
        Payment payment = new Payment(amountPayment);

        // Adding to the database
        paymentRepository.save(payment);

        // Creating a new booking
        Booking booking = new Booking(payment, user, room);

        // Adding to the database
        bookingRepository.save(booking);

        // Creating a request
        String description = "Need some towels";
        boolean fulfilled = false;
        Request request = new Request(description, booking, fulfilled);

        // Adding to the database
        requestRepository.save(request);

        // Creating a service
        Assignment assignment = new Assignment(employee, request);

        assignmentRepository.save(assignment);

        int serviceId = assignment.getAssignmentId();

        // Assertions
        assignment = assignmentRepository.findAssignmentByAssignmentId(serviceId);

        // Assertions
        assertNotNull(assignment);
        assertEquals(request.getRequestId(), assignment.getRequest().getRequestId());
        assertEquals(employee.getEmail(), assignment.getAssignee().getEmail());
    }

}