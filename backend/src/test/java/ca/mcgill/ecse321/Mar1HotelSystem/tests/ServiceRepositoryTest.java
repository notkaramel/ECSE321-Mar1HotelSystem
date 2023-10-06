package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.*;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.HotelScheduleRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
@SpringBootTest
public class ServiceRepositoryTest {
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
    @Autowired
    private ServiceRepository serviceRepository;


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
        serviceRepository.deleteAll();
    }

    @Test
    public void testPersistAndReadService() {
        // Creating an employee
        String firstName = "Candice";
        String lastName = "Evergreen";
        String email = "candice@gmail.com";
        int phoneNumber = 438;
        String password = "BOI100";
        int hoursWorked = 10;
        Employee employee = new Employee(firstName, lastName, email, phoneNumber, password, hoursWorked);

        employeeRepository.save(employee);

        // Creating a user
        String firstNameUser = "Random";
        String lastNameUser = "Dude";
        String emailUser = "Random@email.com";
        int phoneNumberUser = 123;
        GeneralUser user = new GeneralUser(firstNameUser, lastNameUser, emailUser, phoneNumberUser);

        userRepository.save(user);

        // Creating a new custom hour
        Date dateHour = new Date();
        int openingHour = 1;
        int closingHour = 2;
        CustomHours customHours = new CustomHours(dateHour, openingHour, closingHour);

        customHoursRepository.save(customHours);

        // Creating a new operating hour
        OperatingHours.DayOfWeek dayOfWeek = OperatingHours.DayOfWeek.Monday;
        int openingHourOperating = 3;
        int closingHourOperating = 4;
        OperatingHours operatingHours = new OperatingHours(dayOfWeek, openingHourOperating, closingHourOperating);

        operatingHoursRepository.save(operatingHours);

        // Creating a new hotel schedule
        int year = 2023;
        CustomHours[] customHoursList = {customHours};
        OperatingHours[] operatingHoursList = {operatingHours};
        HotelSchedule hotelSchedule = new HotelSchedule(year, operatingHoursList, customHoursList);

        hotelScheduleRepository.save(hotelSchedule);

        // Creating a new hotel
        Hotel hotel = new Hotel(hotelSchedule);

        hotelRepository.save(hotel);

        // Creating a new room
        Room.RoomType roomType = Room.RoomType.Deluxe;
        Room.BedType bedType = Room.BedType.King;
        //boolean flagged = false;
        boolean isAvailable = true;
        int pricePerNight = 5;
        int maxCapacity = 2;
        Room room = new Room(roomType, bedType, isAvailable, pricePerNight, maxCapacity, hotel);

        roomRepository.save(room);

        // Creating a new payment
        int amountPayment = 50;
        Payment payment = new Payment(amountPayment);

        paymentRepository.save(payment);

        // Creating a new booking
        Booking booking = new Booking(payment, user, room);

        bookingRepository.save(booking);

        // Creating a request
        String description = "Need some towels";
        boolean fulfilled = false;
        Request request = new Request(description, employee, booking, fulfilled);

        requestRepository.save(request);

        // Creating a service
        Service service = new Service(employee, request);

        serviceRepository.save(service);

        // Assertions

        // TODO: Find a the service in the database
        //service = serviceRepository.findById();

        assertNotNull(service);
        assertEquals(request, service.getRequest());
    }

}