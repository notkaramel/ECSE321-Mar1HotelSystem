package ca.mcgill.ecse321.Mar1HotelSystem.tests;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

// import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.User;
// import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.BookingRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.UserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        bookingRepository.deleteAll();
        paymentRepository.deleteAll();
        userRepository.deleteAll();
        roomRepository.deleteAll();
    }
}
