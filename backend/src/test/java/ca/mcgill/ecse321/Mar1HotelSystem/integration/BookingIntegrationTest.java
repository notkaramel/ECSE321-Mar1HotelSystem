// package ca.mcgill.ecse321.Mar1HotelSystem.integration;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// import org.apache.catalina.users.GenericUser;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import ca.mcgill.ecse321.Mar1HotelSystem.dao.BookingRepository;
// import ca.mcgill.ecse321.Mar1HotelSystem.dto.BookingRequestDto;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

// public class BookingIntegrationTest {
//     @Autowired
//     private BookingRepository bookingRepository;

//     @Autowired
//     private TestRestTemplate bookingClient;

//     @BeforeEach
//     @AfterEach
//     public void clearDatabase() {
//         bookingRepository.deleteAll();
//     }



//     public BookingRequestDto createBookingRequestDto() {
//         BookingRequestDto bookingRequestDto = new BookingRequestDto();
//         bookingRequestDto.setGeneralUser(new GeneralUser());
//         bookingRequestDto.setPayment(new Payment());
//         bookingRequestDto.setRoom(new Room());
//         return bookingRequestDto;
//     }

    

//     public int testCreateBookingIntegration(BookingRequestDto bookingRequestDto) {
//         Booking booking = new Booking();
//         ResponseEntity<Booking> response = bookingClient.postForEntity("/booking/create", booking, Booking.class);
//         assertNotNull(response);
//         assertNotNull(response.getBody());
//         assertEquals(HttpStatus.CREATED,response.getStatusCode());
//         int bookingId = response.getBody().getBookingId();
//         return bookingId;
//     }

//     public void testGetBookingByIdIntegration(int id) {
//         ResponseEntity<Booking> response = bookingClient.getForEntity("/booking/" + id, Booking.class);
//         assertNotNull(response);
//         assertNotNull(response.getBody());
//         assertEquals(HttpStatus.OK,response.getStatusCode());
//         assertEquals(id, response.getBody().getBookingId());
//     }

//     @Test
// public void testCreateandDeleteBookingByIdIntegration() {
//     BookingRequestDto bookingRequestDto = new BookingRequestDto();
//     int id = testCreateBookingIntegration(bookingRequestDto);
//     ResponseEntity<Void> res = bookingClient.exchange(
//         "/booking/" + id, 
//         HttpMethod.DELETE, 
//         null, 
//         Void.class
//     );
//     assertEquals(HttpStatus.OK, res.getStatusCode());
// }

    
// }
