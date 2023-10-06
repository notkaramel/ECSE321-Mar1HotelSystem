package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;

@SpringBootTest
public class GeneralUserRepositoryTest {
    @Autowired
    private GeneralUserRepository generalUserRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase(){
        generalUserRepository.deleteAll();
    }

    @Test
    public void testPersistAndReadGeneralUser(){
        String firstName = "John";
        String lastName = "Wick";
        String email = "johnwick@mail.com";
        int phoneNumber = 438;


        GeneralUser user = new GeneralUser(firstName, lastName, email, phoneNumber);
        generalUserRepository.save(user);
        String userId = user.getEmail();

        user = generalUserRepository.findGeneralUserByEmail(userId);

        assertNotNull(user);
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
        assertEquals(phoneNumber, user.getPhoneNumber());
    }
}
