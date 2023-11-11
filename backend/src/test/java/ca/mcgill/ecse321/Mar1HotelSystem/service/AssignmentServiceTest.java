package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.AssignmentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Assignment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

/**
 * This class contains test cases for the AssignmentService class.
 * 
 * @author Adam Corbier (@Ad2Am2)
 */

@ExtendWith(MockitoExtension.class)
public class AssignmentServiceTest {

    @Mock
    private AssignmentRepository assignmentRepository;

    @InjectMocks
    private AssignmentService assignmentService;

    private static final int ASSIGNMENT_KEY = 321;
    private static final int NONEXISTING_KEY = 123;

    // Mock DTO
    @BeforeEach
    public void setMockOutput() {
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
        // OperatingHours operatingHoursM = new OperatingHours(DayOfWeek.Monday, 8, 23);
        // OperatingHours operatingHoursT = new OperatingHours(DayOfWeek.Tuesday, 8,
        // 23);
        // OperatingHours operatingHoursW = new OperatingHours(DayOfWeek.Wednesday, 8,
        // 23);
        // OperatingHours operatingHoursR = new OperatingHours(DayOfWeek.Thursday, 8,
        // 23);
        // OperatingHours operatingHoursF = new OperatingHours(DayOfWeek.Friday, 8, 23);
        // OperatingHours operatingHoursS = new OperatingHours(DayOfWeek.Saturday, 8,
        // 23);
        // OperatingHours operatingHoursU = new OperatingHours(DayOfWeek.Sunday, 8, 23);
        // OperatingHours[] operatingHours = {operatingHoursM, operatingHoursT,
        // operatingHoursW, operatingHoursR, operatingHoursF, operatingHoursS,
        // operatingHoursU};
        // CustomHours[] customHours = {};
        // HotelSchedule hotelSchedule = new HotelSchedule(2023, operatingHours,
        // customHours);
        Hotel Mar1Hotel = new Hotel();
        Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
        Booking booking = new Booking(payment, guest, room);
        Request request = new Request("Make my bed please uwu", booking, false);
        Assignment assignment = null;
        try {
            assignment = assignmentService.createAssignment(assignee, request);
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        // Check for null value
        assertNotNull(assignment);
        assertEquals(assignee, assignment.getAssignee());
        assertEquals(request, assignment.getRequest());

    }

    @Test
    public void testCreateAssignmentNullAssignee() {
        Payment payment = new Payment(100);
        GeneralUser guest = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
        // OperatingHours operatingHoursM = new OperatingHours(DayOfWeek.Monday, 8, 23);
        // OperatingHours operatingHoursT = new OperatingHours(DayOfWeek.Tuesday, 8,
        // 23);
        // OperatingHours operatingHoursW = new OperatingHours(DayOfWeek.Wednesday, 8,
        // 23);
        // OperatingHours operatingHoursR = new OperatingHours(DayOfWeek.Thursday, 8,
        // 23);
        // OperatingHours operatingHoursF = new OperatingHours(DayOfWeek.Friday, 8, 23);
        // OperatingHours operatingHoursS = new OperatingHours(DayOfWeek.Saturday, 8,
        // 23);
        // OperatingHours operatingHoursU = new OperatingHours(DayOfWeek.Sunday, 8, 23);
        // OperatingHours[] operatingHours = {operatingHoursM, operatingHoursT,
        // operatingHoursW, operatingHoursR, operatingHoursF, operatingHoursS,
        // operatingHoursU};
        // CustomHours[] customHours = {};
        // HotelSchedule hotelSchedule = new HotelSchedule(2023, operatingHours,
        // customHours);
        Hotel Mar1Hotel = new Hotel();
        Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
        Booking booking = new Booking(payment, guest, room);
        Request request = new Request("Make my bed please uwu", booking, false);

        Assignment assignment = null;
        String error = null;
        HttpStatus errorStatus = null;
        try {
            assignment = assignmentService.createAssignment(null, request);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            errorStatus = e.getStatus();
        }

        assertNull(assignment);
        assertEquals("Assignee cannot be null!", error);
        assertEquals(HttpStatus.BAD_REQUEST, errorStatus);

    }

    @Test
    public void testCreateAssignmentNullRequest() {

        Employee assignee = new Employee("Bob", "Hope", "bobbyhoe@mar1h.com", 1234567890, "password", 0);

        Assignment assignment = null;
        String error = null;
        HttpStatus errorStatus = null;

        try {
            assignment = assignmentService.createAssignment(assignee, null);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            errorStatus = e.getStatus();
        }

        assertNull(assignment);
        assertEquals("Request cannot be null!", error);
        assertEquals(HttpStatus.BAD_REQUEST, errorStatus);

    }

    @Test
    public void testUpdateAssignment() {
        Assignment assignment = null;

        Employee assignee1 = new Employee("Bob", "Hope", "bobbyhoe@mar1h.com", 1234567890, "password", 0);
        Payment payment = new Payment(100);
        GeneralUser guest = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
        // OperatingHours operatingHoursM = new OperatingHours(DayOfWeek.Monday, 8, 23);
        // OperatingHours operatingHoursT = new OperatingHours(DayOfWeek.Tuesday, 8,
        // 23);
        // OperatingHours operatingHoursW = new OperatingHours(DayOfWeek.Wednesday, 8,
        // 23);
        // OperatingHours operatingHoursR = new OperatingHours(DayOfWeek.Thursday, 8,
        // 23);
        // OperatingHours operatingHoursF = new OperatingHours(DayOfWeek.Friday, 8, 23);
        // OperatingHours operatingHoursS = new OperatingHours(DayOfWeek.Saturday, 8,
        // 23);
        // OperatingHours operatingHoursU = new OperatingHours(DayOfWeek.Sunday, 8, 23);
        // OperatingHours[] operatingHours = {operatingHoursM, operatingHoursT,
        // operatingHoursW, operatingHoursR, operatingHoursF, operatingHoursS,
        // operatingHoursU};
        // CustomHours[] customHours = {};
        // HotelSchedule hotelSchedule = new HotelSchedule(2023, operatingHours,
        // customHours);
        Hotel Mar1Hotel = new Hotel();
        Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
        Booking booking = new Booking(payment, guest, room);
        Request request1 = new Request("Make my bed please uwu", booking, false);

        Employee assignee2 = new Employee("Bob", "Hope", "bobbyhoe@mar1h.com", 1234567890, "password", 0);
        Request request2 = new Request("Give me towels please uwu", booking, false);

        assignment = assignmentService.createAssignment(assignee1, request1);
        assignment.setAssignmentID(ASSIGNMENT_KEY);

        try {
            assignment = assignmentService.updateAssignment(ASSIGNMENT_KEY, assignee2, request2);
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        assertNotNull(assignment);
        assertEquals(assignee2, assignment.getAssignee());
        assertEquals(request2, assignment.getRequest());
        assertEquals(ASSIGNMENT_KEY, assignment.getAssignmentId());
    }

    @Test
    public void testUpdateAssignmentNullAssignee() {
        Assignment assignment = null;

        Employee assignee1 = new Employee("Bob", "Hope", "bobbyhoe@mar1h.com", 1234567890, "password", 0);
        Payment payment = new Payment(100);
        GeneralUser guest = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
        // OperatingHours operatingHoursM = new OperatingHours(DayOfWeek.Monday, 8, 23);
        // OperatingHours operatingHoursT = new OperatingHours(DayOfWeek.Tuesday, 8,
        // 23);
        // OperatingHours operatingHoursW = new OperatingHours(DayOfWeek.Wednesday, 8,
        // 23);
        // OperatingHours operatingHoursR = new OperatingHours(DayOfWeek.Thursday, 8,
        // 23);
        // OperatingHours operatingHoursF = new OperatingHours(DayOfWeek.Friday, 8, 23);
        // OperatingHours operatingHoursS = new OperatingHours(DayOfWeek.Saturday, 8,
        // 23);
        // OperatingHours operatingHoursU = new OperatingHours(DayOfWeek.Sunday, 8, 23);
        // OperatingHours[] operatingHours = {operatingHoursM, operatingHoursT,
        // operatingHoursW, operatingHoursR, operatingHoursF, operatingHoursS,
        // operatingHoursU};
        // CustomHours[] customHours = {};
        // HotelSchedule hotelSchedule = new HotelSchedule(2023, operatingHours,
        // customHours);
        Hotel Mar1Hotel = new Hotel();
        Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
        Booking booking = new Booking(payment, guest, room);
        Request request1 = new Request("Make my bed please uwu", booking, false);

        Request request2 = new Request("Give me towels please uwu", booking, false);

        assignment = assignmentService.createAssignment(assignee1, request1);
        assignment.setAssignmentID(ASSIGNMENT_KEY);

        String error = null;
        HttpStatus errorStatus = null;

        try {
            assignment = assignmentService.updateAssignment(ASSIGNMENT_KEY, null, request2);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            errorStatus = e.getStatus();
        }

        assertNotNull(assignment);
        assertEquals("Assignee cannot be null!", error);
        assertEquals(HttpStatus.BAD_REQUEST, errorStatus);

    }

    @Test
    public void testUpdateAssignmentNullRequest() {
        Assignment assignment = null;

        Employee assignee1 = new Employee("Bob", "Hope", "bobbyhoe@mar1h.com", 1234567890, "password", 0);
        Payment payment = new Payment(100);
        GeneralUser guest = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
        // OperatingHours operatingHoursM = new OperatingHours(DayOfWeek.Monday, 8, 23);
        // OperatingHours operatingHoursT = new OperatingHours(DayOfWeek.Tuesday, 8,
        // 23);
        // OperatingHours operatingHoursW = new OperatingHours(DayOfWeek.Wednesday, 8,
        // 23);
        // OperatingHours operatingHoursR = new OperatingHours(DayOfWeek.Thursday, 8,
        // 23);
        // OperatingHours operatingHoursF = new OperatingHours(DayOfWeek.Friday, 8, 23);
        // OperatingHours operatingHoursS = new OperatingHours(DayOfWeek.Saturday, 8,
        // 23);
        // OperatingHours operatingHoursU = new OperatingHours(DayOfWeek.Sunday, 8, 23);
        // OperatingHours[] operatingHours = {operatingHoursM, operatingHoursT,
        // operatingHoursW, operatingHoursR, operatingHoursF, operatingHoursS,
        // operatingHoursU};
        // CustomHours[] customHours = {};
        // HotelSchedule hotelSchedule = new HotelSchedule(2023, operatingHours,
        // customHours);
        Hotel Mar1Hotel = new Hotel();
        Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
        Booking booking = new Booking(payment, guest, room);
        Request request1 = new Request("Make my bed please uwu", booking, false);

        Employee assignee2 = new Employee("Bob", "Hope", "bobbyhoe@mar1h.com", 1234567890, "password", 0);

        assignment = assignmentService.createAssignment(assignee1, request1);
        assignment.setAssignmentID(ASSIGNMENT_KEY);

        String error = null;
        HttpStatus errorStatus = null;

        try {
            assignment = assignmentService.updateAssignment(ASSIGNMENT_KEY, assignee2, null);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            errorStatus = e.getStatus();
        }

        assertNotNull(assignment);
        assertEquals("Request cannot be null!", error);
        assertEquals(HttpStatus.BAD_REQUEST, errorStatus);
    }

    @Test
    public void testGetAssignmentById() {
        Assignment assignment = null;
        try {
            assignment = assignmentService.getAssignmentById(ASSIGNMENT_KEY);
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        assertNotNull(assignment);
        assertEquals(ASSIGNMENT_KEY, assignment.getAssignmentId());
    }

    @Test
    public void testGetAssignmentByInvalidId() {
        Assignment assignment = null;
        String error = null;
        HttpStatus errorStatus = null;
        try {
            assignment = assignmentService.getAssignmentById(NONEXISTING_KEY);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            errorStatus = e.getStatus();
        }
        assertNull(assignment);
        assertEquals("No assignment found with ID: " + NONEXISTING_KEY, error);
        assertEquals(HttpStatus.NOT_FOUND, errorStatus);

    }

    @Test
    public void testGetAllAssignments() {
        assertEquals(1, assignmentService.getAllAssignments().size());
        assertEquals(ASSIGNMENT_KEY, assignmentService.getAllAssignments().get(0).getAssignmentId());
    }

    @Test
    public void testDeleteAssignment() {
        Assignment assignment = null;
        try {
            assignment = assignmentService.getAssignmentById(ASSIGNMENT_KEY);
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        assertNotNull(assignment);
        assertEquals(ASSIGNMENT_KEY, assignment.getAssignmentId());
        assertEquals(1, assignmentService.getAllAssignments().size());

        boolean deleted = false;
        try {
            deleted = assignmentService.deleteAssignment(ASSIGNMENT_KEY);
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        assertTrue(deleted);
    }

    @Test
    public void testDeleteNonExistingAssignment() {
        Assignment assignment = null;
        try {
            assignment = assignmentService.getAssignmentById(ASSIGNMENT_KEY);
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        assertNotNull(assignment);
        assertEquals(ASSIGNMENT_KEY, assignment.getAssignmentId());
        assertEquals(1, assignmentService.getAllAssignments().size());

        boolean deleted = false;
        String error = null;
        HttpStatus errorStatus = null;
        try {
            deleted = assignmentService.deleteAssignment(NONEXISTING_KEY);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            errorStatus = e.getStatus();
        }

        assertFalse(deleted);
        assertEquals("No assignment found with ID: " + NONEXISTING_KEY, error);
        assertEquals(HttpStatus.NOT_FOUND, errorStatus);
    }
}
