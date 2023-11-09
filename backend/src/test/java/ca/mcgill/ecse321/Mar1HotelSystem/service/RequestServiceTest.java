// package ca.mcgill.ecse321.Mar1HotelSystem.service;

// import ca.mcgill.ecse321.Mar1HotelSystem.dao.RequestRepository;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.HttpStatus;

// import java.util.ArrayList;
// import java.util.Iterator;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyInt;
// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.Mockito.lenient;
// import static org.mockito.Mockito.when;


// /**
//  * This class contains test cases for the RequestService class.
//  * 
//  * @author Adam (@Ad2Am2)
//  */
// @ExtendWith(MockitoExtension.class)
// public class RequestServiceTest {


//     @Mock
//     private RequestRepository requestRepository;

//     @InjectMocks
//     private RequestService requestService;

//     private static final int REQUEST_KEY = 321;
//     private static final int NONEXISTING_KEY = 123;

//     // Mock DTO
//     @BeforeEach
//     public void setMockOutput() {
//         lenient().when(requestRepository.findRequestByRequestId(anyInt())).thenAnswer((invocation) -> {
//             if (invocation.getArgument(0).equals(REQUEST_KEY)) {
//                 Request request = new Request();
//                 request.setRequestId(REQUEST_KEY);
//                 return request;
//             } else {
//                 return null;
//             }
//         });
//         lenient().when(requestRepository.findAll()).thenAnswer((invocation) -> {
//             ArrayList<Request> requests = new ArrayList<Request>();
//             Request request = new Request();
//             request.setRequestId(REQUEST_KEY);
//             requests.add(request);
//             return requests;
//         });
//     }

//     @Test
//     public void testCreateRequest() {
//         assertEquals(0, requestRepository.count());

//         Payment payment = new Payment(100);
//         GeneralUser guest = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
//         OperatingHours operatingHoursM = new OperatingHours(DayOfWeek.Monday, 8, 23);
//         OperatingHours operatingHoursT = new OperatingHours(DayOfWeek.Tuesday, 8, 23);
//         OperatingHours operatingHoursW = new OperatingHours(DayOfWeek.Wednesday, 8, 23);
//         OperatingHours operatingHoursR = new OperatingHours(DayOfWeek.Thursday, 8, 23);
//         OperatingHours operatingHoursF = new OperatingHours(DayOfWeek.Friday, 8, 23);
//         OperatingHours operatingHoursS = new OperatingHours(DayOfWeek.Saturday, 8, 23);
//         OperatingHours operatingHoursU = new OperatingHours(DayOfWeek.Sunday, 8, 23);
//         OperatingHours[] operatingHours = {operatingHoursM, operatingHoursT, operatingHoursW, operatingHoursR, operatingHoursF, operatingHoursS, operatingHoursU};
//         CustomHours[] customHours = {};
//         HotelSchedule hotelSchedule = new HotelSchedule(2023, operatingHours, customHours);
//         Hotel Mar1Hotel = new Hotel(hotelSchedule);
//         Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
//         Booking booking = new Booking(payment, guest, room);

//         Request request = null;

//         try {
//             request = requestService.createRequest("Make my bed please uwu", booking, false);
//         } catch (IllegalArgumentException e) {
//             fail();
//         }

//         assertNotNull(request);
//         assertEquals(booking, request.getBooking());
//         assertEquals("Make my bed please uwu", request.getDescription());
//         assertFalse(request.getIsFufilled());

//     }


//     @Test
//     public void testUpdateRequestDescription() {
//         Request request = null;

//         Payment payment = new Payment(100);
//         GeneralUser guest = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
//         OperatingHours operatingHoursM = new OperatingHours(DayOfWeek.Monday, 8, 23);
//         OperatingHours operatingHoursT = new OperatingHours(DayOfWeek.Tuesday, 8, 23);
//         OperatingHours operatingHoursW = new OperatingHours(DayOfWeek.Wednesday, 8, 23);
//         OperatingHours operatingHoursR = new OperatingHours(DayOfWeek.Thursday, 8, 23);
//         OperatingHours operatingHoursF = new OperatingHours(DayOfWeek.Friday, 8, 23);
//         OperatingHours operatingHoursS = new OperatingHours(DayOfWeek.Saturday, 8, 23);
//         OperatingHours operatingHoursU = new OperatingHours(DayOfWeek.Sunday, 8, 23);
//         OperatingHours[] operatingHours = {operatingHoursM, operatingHoursT, operatingHoursW, operatingHoursR, operatingHoursF, operatingHoursS, operatingHoursU};
//         CustomHours[] customHours = {};
//         HotelSchedule hotelSchedule = new HotelSchedule(2023, operatingHours, customHours);
//         Hotel Mar1Hotel = new Hotel(hotelSchedule);
//         Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
//         Booking booking = new Booking(payment, guest, room);

//         request = requestService.createRequest("Make my bed please uwu", booking, false);
//         request.setRequestId(REQUEST_KEY);

//         try{
//             request = requestService.updateRequestDescriptionByRequestId(request.getRequestId(), "Gimme towels please uwu");
//         } catch (IllegalArgumentException e) {
//             fail();
//         }

//         assertNotNull(request);
//         assertEquals("Gimme towels please uwu", request.getDescription());
//     }



//     @Test
//     public void testDeleteRequest() {


//         boolean isDeleted = false;

//         Payment payment = new Payment(100);
//         GeneralUser guest = new GeneralUser("Guesty", "Guest", "guesty.guest@gmail.com", 1234567890);
//         OperatingHours operatingHoursM = new OperatingHours(DayOfWeek.Monday, 8, 23);
//         OperatingHours operatingHoursT = new OperatingHours(DayOfWeek.Tuesday, 8, 23);
//         OperatingHours operatingHoursW = new OperatingHours(DayOfWeek.Wednesday, 8, 23);
//         OperatingHours operatingHoursR = new OperatingHours(DayOfWeek.Thursday, 8, 23);
//         OperatingHours operatingHoursF = new OperatingHours(DayOfWeek.Friday, 8, 23);
//         OperatingHours operatingHoursS = new OperatingHours(DayOfWeek.Saturday, 8, 23);
//         OperatingHours operatingHoursU = new OperatingHours(DayOfWeek.Sunday, 8, 23);
//         OperatingHours[] operatingHours = {operatingHoursM, operatingHoursT, operatingHoursW, operatingHoursR, operatingHoursF, operatingHoursS, operatingHoursU};
//         CustomHours[] customHours = {};
//         HotelSchedule hotelSchedule = new HotelSchedule(2023, operatingHours, customHours);
//         Hotel Mar1Hotel = new Hotel(hotelSchedule);
//         Room room = new Room(RoomType.Regular, BedType.King, true, 100, 2, Mar1Hotel);
//         Booking booking = new Booking(payment, guest, room);

//         Request request = requestService.createRequest("Make my bed please uwu", booking, false);
//         request.setRequestId(REQUEST_KEY);

//         try {
//             isDeleted = requestService.deleteRequestById(request.getRequestId());
//         } catch (IllegalArgumentException e) {
//             fail();
//         }

//         assertTrue(isDeleted);
//         assertEquals(0, requestRepository.count());
//     }


//     @Test
//     public void testGetRequestById() {

//         Request request = null;

//         try{
//             request = requestRepository.findRequestByRequestId(REQUEST_KEY);
//         } catch (IllegalArgumentException e) {
//             fail();
//         }

//         assertNotNull(request);
//         assertEquals(REQUEST_KEY, request.getRequestId());

//     }


//     @Test
//     public void testGetAllRequests() {

//         ArrayList<Request> requests = new ArrayList<Request>();

//         try{
//             requests = (ArrayList<Request>) requestRepository.findAll();
//         } catch (IllegalArgumentException e) {
//             fail();
//         }

//         assertNotNull(requests);
//         assertEquals(1, requests.size());
//         assertEquals(REQUEST_KEY, requests.get(0).getRequestId());

//     }


//     // That's all I can come up with rn


// }

