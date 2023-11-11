package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.RequestRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;

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
 * This class contains test cases for the RequestService class.
 * 
 * @author Adam (@Ad2Am2)
 */
@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {

    @Mock
    private RequestRepository requestRepository;

    @InjectMocks
    private RequestService requestService;

    private static final int REQUEST_KEY = 321;
    private static final int NONEXISTING_KEY = 123;

    // Mock DTO
    @BeforeEach
    public void setMockOutput() {
        lenient().when(requestRepository.findRequestByRequestId(anyInt())).thenAnswer((invocation) -> {
            if (invocation.getArgument(0).equals(REQUEST_KEY)) {
                Request request = new Request();
                request.setRequestId(REQUEST_KEY);
                return request;
            } else {
                return null;
            }
        });
        lenient().when(requestRepository.findAll()).thenAnswer((invocation) -> {
            ArrayList<Request> requests = new ArrayList<Request>();
            Request request = new Request();
            request.setRequestId(REQUEST_KEY);
            requests.add(request);
            return requests;
        });
    }

    @Test
    public void testCreateRequest() {
        assertEquals(0, requestRepository.count());

        Payment payment = new Payment(100);
        GeneralUser guest = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
        Hotel Mar1Hotel = new Hotel();
        Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
        Booking booking = new Booking(payment, guest, room);

        Request request = null;

        try {
            request = requestService.createRequest("Make my bed please uwu", booking, false);
        } catch (Mar1HotelSystemException e) {
            fail();
        }

        assertNotNull(request);
        assertEquals(booking, request.getBooking());
        assertEquals("Make my bed please uwu", request.getDescription());
        assertFalse(request.getIsFulfilled());

    }

    @Test
    public void testCreateRequestNullDescription() {

        assertEquals(0, requestRepository.count());

        Payment payment = new Payment(100);
        GeneralUser guest = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
        Hotel Mar1Hotel = new Hotel();
        Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
        Booking booking = new Booking(payment, guest, room);

        Request request = null;

        String error = null;
        HttpStatus status = null;

        try {
            request = requestService.createRequest(null, booking, false);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            status = e.getStatus();
        }

        assertNull(request);
        assertEquals("Request description cannot be null!", error);
        assertEquals(HttpStatus.BAD_REQUEST, status);

    }

    @Test
    public void testCreateRequestEmptyDescription() {

        assertEquals(0, requestRepository.count());

        Payment payment = new Payment(100);
        GeneralUser guest = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
        Hotel Mar1Hotel = new Hotel();
        Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
        Booking booking = new Booking(payment, guest, room);

        Request request = null;

        String error = null;
        HttpStatus status = null;

        try {
            request = requestService.createRequest("", booking, false);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            status = e.getStatus();
        }

        assertNull(request);
        assertEquals("Request description cannot be empty!", error);
        assertEquals(HttpStatus.BAD_REQUEST, status);

    }

    @Test
    public void testCreateRequestNullBooking() {
        assertEquals(0, requestRepository.count());

        Request request = null;
        String error = null;
        HttpStatus status = null;

        try {
            request = requestService.createRequest("Make my bed please uwu", null, false);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            status = e.getStatus();
        }

        assertNull(request);
        assertEquals("Booking cannot be null!", error);
        assertEquals(HttpStatus.BAD_REQUEST, status);
    }

    @Test
    public void testUpdateRequestDescription() {
        Request request = null;

        Payment payment = new Payment(100);
        GeneralUser guest = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
        Hotel Mar1Hotel = new Hotel();
        Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
        Booking booking = new Booking(payment, guest, room);

        request = requestService.createRequest("Make my bed please uwu", booking, false);
        request.setRequestId(REQUEST_KEY);

        try {
            request = requestService.updateRequestDescriptionByRequestId(request.getRequestId(),
                    "Gimme towels please uwu");
        } catch (Mar1HotelSystemException e) {
            fail();
        }

        assertNotNull(request);
        assertEquals("Gimme towels please uwu", request.getDescription());
    }

    @Test
    public void testUpdateNullRequestDescription() {
        Request request = null;

        Payment payment = new Payment(100);
        GeneralUser guest = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
        Hotel Mar1Hotel = new Hotel();
        Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
        Booking booking = new Booking(payment, guest, room);

        request = requestService.createRequest("Make my bed please uwu", booking, false);
        request.setRequestId(REQUEST_KEY);

        String error = null;
        HttpStatus status = null;

        try {
            request = requestService.updateRequestDescriptionByRequestId(request.getRequestId(), null);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            status = e.getStatus();
        }

        assertNotNull(request);
        assertEquals("Make my bed please uwu", request.getDescription());
        assertEquals(booking, request.getBooking());
        assertEquals("Request description cannot be null!", error);
        assertEquals(HttpStatus.BAD_REQUEST, status);

    }

    @Test
    public void testUpdateRequestBooking() {
        Request request = null;

        Payment payment1 = new Payment(100);
        GeneralUser guest1 = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
        Hotel Mar1Hotel = new Hotel();
        Room room1 = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
        Booking booking1 = new Booking(payment1, guest1, room1);

        Payment payment2 = new Payment(200);
        Customer guest2 = new Customer("Customery", "Customer", "omgimsuchatourist@awesome.com", 8008135,
                "supersecurepassword");
        Room room2 = new Room(RoomType.Deluxe, BedType.Queen, true, 200, 2, Mar1Hotel);
        Booking booking2 = new Booking(payment2, guest2, room2);

        request = requestService.createRequest("Make my bed please uwu", booking1, false);
        request.setRequestId(REQUEST_KEY);

        try {
            request = requestService.updateBookingByRequestId(request.getRequestId(), booking2);
        } catch (Mar1HotelSystemException e) {
            fail();
        }

        assertNotNull(request);
        assertEquals(booking2, request.getBooking());
    }

    @Test
    public void testUpdateNullRequestBooking() {
        Request request = null;

        Payment payment1 = new Payment(100);
        GeneralUser guest1 = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
        Hotel Mar1Hotel = new Hotel();
        Room room1 = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
        Booking booking1 = new Booking(payment1, guest1, room1);

        request = requestService.createRequest("Make my bed please uwu", booking1, false);
        request.setRequestId(REQUEST_KEY);

        String error = null;
        HttpStatus status = null;

        try {
            request = requestService.updateBookingByRequestId(request.getRequestId(), null);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            status = e.getStatus();
        }

        assertNotNull(request);
        assertEquals("Make my bed please uwu", request.getDescription());
        assertEquals(booking1, request.getBooking());
        assertEquals("Booking cannot be null!", error);
        assertEquals(HttpStatus.BAD_REQUEST, status);
    }

    @Test
    public void testDeleteRequest() {
        boolean isDeleted = false;

        Payment payment = new Payment(100);
        GeneralUser guest = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
        Hotel Mar1Hotel = new Hotel();
        Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
        Booking booking = new Booking(payment, guest, room);

        Request request = requestService.createRequest("Make my bed please uwu", booking, false);
        request.setRequestId(REQUEST_KEY);

        try {
            isDeleted = requestService.deleteRequestById(request.getRequestId());
        } catch (Mar1HotelSystemException e) {
            fail();
        }

        assertTrue(isDeleted);
    }

    @Test
    public void testDeleteNonexistingRequest() {
        Payment payment = new Payment(100);
        GeneralUser guest = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
        Hotel Mar1Hotel = new Hotel();
        Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
        Booking booking = new Booking(payment, guest, room);

        Request request = requestService.createRequest("Make my bed please uwu", booking, false);
        request.setRequestId(REQUEST_KEY);

        String error = null;
        HttpStatus status = null;

        try {
            requestService.deleteRequestById(NONEXISTING_KEY);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            status = e.getStatus();
        }

        assertEquals("Request with id " + NONEXISTING_KEY + " does not exist!", error);
        assertEquals(HttpStatus.NOT_FOUND, status);
    }

    @Test
    public void testGetRequestById() {

        Request request = null;

        try {
            request = requestService.getRequestById(REQUEST_KEY);
        } catch (Mar1HotelSystemException e) {
            fail();
        }

        assertNotNull(request);
        assertEquals(REQUEST_KEY, request.getRequestId());

    }

    @Test
    public void testGetRequestByInvalidId() {

        Request request = null;

        String error = null;
        HttpStatus status = null;

        try {
            request = requestService.getRequestById(NONEXISTING_KEY);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            status = e.getStatus();
        }

        assertNull(request);
        assertEquals("Request with id " + NONEXISTING_KEY + " does not exist!", error);
        assertEquals(HttpStatus.NOT_FOUND, status);

    }

    @Test
    public void testGetAllRequests() {

        ArrayList<Request> requests = new ArrayList<Request>();

        try {
            requests = (ArrayList<Request>) requestService.getAllRequests();
        } catch (Mar1HotelSystemException e) {
            fail();
        }

        assertNotNull(requests);
        assertEquals(1, requests.size());
        assertEquals(REQUEST_KEY, requests.get(0).getRequestId());

    }

    @Test
    public void testDeleteAllRequests() {
        boolean isDeleted = false;
        try {
            isDeleted = requestService.deleteAllRequests();
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        assertTrue(isDeleted);

    }
}
