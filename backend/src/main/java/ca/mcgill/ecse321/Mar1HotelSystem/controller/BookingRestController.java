package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.dto.BookingRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.CustomerDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.GeneralUserDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.PaymentRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RoomRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.service.BookingService;
import ca.mcgill.ecse321.Mar1HotelSystem.service.Mar1HotelSystemService;
import ca.mcgill.ecse321.Mar1HotelSystem.service.RoomService;

/**
 * The controller that handles /booking endpoint requests
 * Functionalities:
 * - Get all bookings (GET /bookings)
 * - Get booking by ID (GET /bookings/{bookingId})
 * - Create a booking (POST /bookings)
 *   - Request body: BookingRequestDto schema
 * - Update a booking (PUT /bookings/{bookingId})
 *   - Request body: BookingRequestDto schema
 * - Delete a booking (DELETE /bookings/{bookingId})
 * 
 * @bmokhtari Bilar Mokhtari
 */

@RestController
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @Autowired
    Mar1HotelSystemService hotelService; 

    private RoomRequestDto roomRequestDto;

    @DeleteMapping(value = { "/booking/delete/{bookingId}", "/booking/{bookingId}/" })
    @ResponseStatus(HttpStatus.OK)
    public void deleteBooking(@PathVariable int bookingId) {
        bookingService.deleteBooking(bookingId);
    }

    @GetMapping(value = { "/booking/{bookingId}", "/booking/{bookingId}/" })
    @ResponseStatus(HttpStatus.OK)
    public void getBookingById(@PathVariable int bookingId) {
        bookingService.getBookingById(bookingId);
    }

    @GetMapping(value = { "/booking/all", "/booking/all" })
    @ResponseStatus(HttpStatus.OK)
    public void getAllBookings() {
        bookingService.getAllBookings();
    }

    @PostMapping(value = { "/booking/create", "/booking/create" })
    @ResponseStatus(HttpStatus.CREATED)
    public void createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        GeneralUser generalUser = new GeneralUser(bookingRequestDto.getGeneralUser().getFirstName(), bookingRequestDto.getGeneralUser().getLastName(), bookingRequestDto.getGeneralUser().getEmail(), bookingRequestDto.getGeneralUser().getPhoneNumber());
        Payment payment = new Payment(bookingRequestDto.getPayment().getAmount());
        Room room = createRoom(bookingRequestDto.getRoom());
        bookingService.createBooking(payment, generalUser, room);
    }

    @PutMapping(value = { "/booking/update/{bookingId}", "/booking/update/{bookingId}/" })
    @ResponseStatus(HttpStatus.OK)
    public void updateBooking(@PathVariable int bookingId, @RequestBody BookingRequestDto bookingRequestDto) {
        GeneralUser generalUser = new GeneralUser(bookingRequestDto.getGeneralUser().getFirstName(), bookingRequestDto.getGeneralUser().getLastName(), bookingRequestDto.getGeneralUser().getEmail(), bookingRequestDto.getGeneralUser().getPhoneNumber());
        Payment payment = new Payment(bookingRequestDto.getPayment().getAmount());
        Booking booking = bookingService.getBookingById(bookingId);
        booking.setGeneralUser(generalUser);
        booking.setPayment(payment);
        booking.setRoom(createRoom(roomRequestDto)); 
        bookingService.updateBooking(booking);
    }


    public Room createRoom(RoomRequestDto roomRequestDto) {
        try {
            hotelService.getHotel();
        } catch (Mar1HotelSystemException e) {
            hotelService.createHotel();
        }
        Room.RoomType roomType = roomRequestDto.getRoomType();
        Room.BedType bedType = roomRequestDto.getBedType();
        boolean isAvailable = roomRequestDto.getIsAvailable();
        int pricePerNight = roomRequestDto.getPricePerNight();
        int maxCapacity = roomRequestDto.getMaxCapacity();

        Room room = roomService.createRoom(roomType, bedType, isAvailable, pricePerNight, maxCapacity);
        return room;
        
    }

    public void setUp(){
        createPaymentRequestDto(100);
        createGeneralUserDto("Joe","John", "joe@mail.com", 514514514);
    //     createRoomRequestDto(, null, false, 0, 0)
     } 

    public GeneralUserDto createGeneralUserDto(String firstName, String lastName, String email, long phoneNumber) {
        GeneralUserDto generalUserDto = new GeneralUserDto();
        generalUserDto.setFirstName(firstName);
        generalUserDto.setLastName(lastName);
        generalUserDto.setEmail(email);
        generalUserDto.setPhoneNumber(phoneNumber);
        return generalUserDto;
    }

    public PaymentRequestDto createPaymentRequestDto(int amount) {
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setAmount(amount);
        return paymentRequestDto;
    }

    public RoomRequestDto createRoomRequestDto(Room.RoomType roomType, Room.BedType bedType, boolean isAvailable, int pricePerNight, int maxCapacity){
        
        RoomRequestDto roomRequestDto = new RoomRequestDto(roomType, bedType, isAvailable, pricePerNight, maxCapacity);

        return roomRequestDto;
        
    }



    public BookingRequestDto createBookingRequestDto(PaymentRequestDto paymentRequestDto, GeneralUserDto generalUserDto, RoomRequestDto roomRequestDto) {
        BookingRequestDto bookingRequestDto = new BookingRequestDto();
        bookingRequestDto.setPayment(paymentRequestDto);
        bookingRequestDto.setGeneralUser(generalUserDto);
        bookingRequestDto.setRoom(roomRequestDto);
        return bookingRequestDto;
    }
 }
