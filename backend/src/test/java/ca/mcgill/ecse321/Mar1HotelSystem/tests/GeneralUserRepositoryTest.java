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
/**
 * Test class for the GeneralUser repository.
 * 
 * @author Mokhtari, Bilar
 */
@SpringBootTest
public class GeneralUserRepositoryTest {
    @Autowired
    private GeneralUserRepository generalUserRepository;

    @BeforeEach
    @AfterEach

    // Clear the database before and after each test
    public void clearDatabase(){
        generalUserRepository.deleteAll();
    }

    @Test
    public void testPersistAndReadGeneralUser(){
        //Creating variables necessary for user to test our repository
        String firstName = "John";
        String lastName = "Wick";
        String email = "johnwick@mail.com";
        int phoneNumber = 438;

        // Create and save the GeneralUser
        GeneralUser generalUser = new GeneralUser(firstName, lastName, email, phoneNumber);
        generalUserRepository.save(generalUser);
        String userId = generalUser.getEmail();

        //Testing read
        generalUser = generalUserRepository.findGeneralUserByEmail(userId);

        // Assertions
        assertNotNull(generalUser);
        assertEquals(firstName, generalUser.getFirstName());
        assertEquals(lastName, generalUser.getLastName());
        assertEquals(email, generalUser.getEmail());
        assertEquals(phoneNumber, generalUser.getPhoneNumber());
    }
}
