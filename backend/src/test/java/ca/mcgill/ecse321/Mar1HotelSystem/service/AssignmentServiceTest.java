package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.AssignmentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Assignment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AssignmentServiceTest {

    @Mock
    private AssignmentRepository assignmentRepository;

    @InjectMocks
    private AssignmentService assignmentService;

    private static final int ASSIGNMENT_KEY = 321;
    private static final int NONEXISTING_KEY = 123;

    @BeforeEach
    public void setMockOutput(Assignment mockAssignment) {
        lenient().when(assignmentRepository.findAssignmentByAssignmentId(anyInt())).thenAnswer((invocation) -> {
            if (invocation.getArgument(0).equals(ASSIGNMENT_KEY)) {
                Assignment assignment = new Assignment();
                assignment.setAssignmentID(ASSIGNMENT_KEY);
                return assignment;
            } else {
                return null;
            }
        });
        lenient().when(assignmentRepository.findAll()).thenAnswer((invocation) -> {
            ArrayList<Assignment> assignments = new ArrayList<Assignment>();
            Assignment assignment = new Assignment();
            assignment.setAssignmentID(ASSIGNMENT_KEY);
            assignments.add(assignment);
            return assignments;
        });
    }


    @Test
    public void testCreateAssignment() {
        assertEquals(0, assignmentRepository.count());
        Employee assignee = new Employee("Bob", "Hope", "bobbyhoe@mar1h.com", 1234567890, "password", 0);
        Payment payment = new Payment(100);
        GeneralUser guest = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
        OperatingHours operatingHoursM = new OperatingHours(DayOfWeek.Monday, 8, 23);
        OperatingHours operatingHoursT = new OperatingHours(DayOfWeek.Tuesday, 8, 23);
        OperatingHours operatingHoursW = new OperatingHours(DayOfWeek.Wednesday, 8, 23);
        OperatingHours operatingHoursR = new OperatingHours(DayOfWeek.Thursday, 8, 23);
        OperatingHours operatingHoursF = new OperatingHours(DayOfWeek.Friday, 8, 23);
        OperatingHours operatingHoursS = new OperatingHours(DayOfWeek.Saturday, 8, 23);
        OperatingHours operatingHoursU = new OperatingHours(DayOfWeek.Sunday, 8, 23);
        OperatingHours[] operatingHours = {operatingHoursM, operatingHoursT, operatingHoursW, operatingHoursR, operatingHoursF, operatingHoursS, operatingHoursU};
        CustomHours[] customHours = {};
        HotelSchedule hotelSchedule = new HotelSchedule(2023, operatingHours, customHours);
        Hotel Mar1Hotel = new Hotel(hotelSchedule);
        Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
        Booking booking = new Booking(payment, guest, room);
        Request request = new Request("Make my bed please uwu", booking, false);
        Assignment assignment = null;
        try {
            assignment = assignmentService.createAssignment(assignee, request);
        } catch (IllegalArgumentException e) {
            fail();
        }
        // Check for null value
        assertNotNull(assignment);
    }





    
}
