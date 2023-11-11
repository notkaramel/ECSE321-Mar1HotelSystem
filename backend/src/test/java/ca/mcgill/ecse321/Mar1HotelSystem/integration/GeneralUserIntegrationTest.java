package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.GeneralUserDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.MultipleGeneralUserDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@TestInstance(Lifecycle.PER_CLASS)

/**
 * General User Integration Tests
 * 
 * @author Lucas Pacicco (@Lucaspac5)
 * 
 */
public class GeneralUserIntegrationTest {

    @Autowired
    private GeneralUserRepository generalUserRepo;

    @Autowired
    private TestRestTemplate client;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        generalUserRepo.deleteAll();
    }

    /*
     * Test to get error message indicating list of general users is empty because
     * there are no general users created yet
     */
    @Test
    @Order(0)
    public void testGetGeneralUsersInvalid() {
        ResponseEntity<String> response = client.getForEntity("/generalUsers", String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("There are no Users found!", response.getBody());
    }

    /*
     * Test successfully creating a general user
     */
    @Test
    @Order(1)
    public void testCreateGeneralUserValid() {
        String firstName = "Joe";
        String lastName = "Doe";
        String email = "joe@gmail.com";
        long phoneNumber = 1234567891;
        GeneralUserDto generalUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);
        ResponseEntity<GeneralUserDto> response = client.postForEntity("/generalUsers/create", generalUserDto,
                GeneralUserDto.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(firstName, response.getBody().getFirstName());
        assertEquals(lastName, response.getBody().getLastName());
        assertEquals(email, response.getBody().getEmail());
        assertEquals(phoneNumber, response.getBody().getPhoneNumber());

    }

    /*
     * Test to successfully get a specific general user
     */
    @Test
    @Order(2)
    public void testGetSpecificGeneralUserValid() {

        GeneralUserDto user = createGeneralUser();

        ResponseEntity<GeneralUserDto> response = client.getForEntity("/generalUsers/" + "joe@gmail.com",
                GeneralUserDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user.getFirstName(), response.getBody().getFirstName());
        assertEquals(user.getLastName(), response.getBody().getLastName());
        assertEquals(user.getEmail(), response.getBody().getEmail());
        assertEquals(user.getPhoneNumber(), response.getBody().getPhoneNumber());

    }

    /*
     * Test to unsuccessfully get a general user that does not exist
     */
    @Test
    @Order(3)
    public void testGetSpecificGeneralUserInvalid() {
        GeneralUserDto user = createGeneralUser();
        ResponseEntity<String> response = client.getForEntity("/generalUsers/" + "hey@gmail.com", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User Not Found", response.getBody());

    }

    /*
     * Test to unsucessfully create another general user with same characteristics
     */
    @Test
    @Order(4)
    public void testCreateSecondGeneralUserInavlid1() {
        GeneralUserDto user = createGeneralUser();
        String firstName = "Joe";
        String lastName = "Doe";
        String email = "joe@gmail.com";
        long phoneNumber = 1234567891;

        GeneralUserDto generalUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);

        ResponseEntity<String> response = client.postForEntity("/generalUsers/create", generalUserDto, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User with that email already exists!", response.getBody());
    }

    /*
     * Test to successfully create a second general user
     */
    @Test
    @Order(5)
    public void testCreateSecondGeneralUserValid() {
        GeneralUserDto user = createGeneralUser();
        String firstName = "Joey";
        String lastName = "Doey";
        String email = "joey@gmail.com";
        long phoneNumber = 1234567891;
        GeneralUserDto generalUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);

        ResponseEntity<GeneralUserDto> response = client.postForEntity("/generalUsers/create", generalUserDto,
                GeneralUserDto.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(firstName, response.getBody().getFirstName());
        assertEquals(lastName, response.getBody().getLastName());
        assertEquals(email, response.getBody().getEmail());
        assertEquals(phoneNumber, response.getBody().getPhoneNumber());
    }

    /*
     * Test to successfully delete a general user
     */
    @Test
    @Order(6)
    public void testDeleteGeneralUserValid() {
        GeneralUserDto generalUser = createGeneralUser();
        ResponseEntity<Boolean> response = client.postForEntity("/generalUsers/delete", generalUser, Boolean.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    /*
     * Test to unsuccessfully create a general user because a field is empty
     */
    @Test
    @Order(7)
    public void testCreateGeneralUserInvalid2() {
        String firstName = "";
        String lastName = "Doe";
        String email = "joe@gmail.com";
        long phoneNumber = 1234567891;
        GeneralUserDto generalUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);

        ResponseEntity<String> response = client.postForEntity("/generalUsers/create", generalUserDto, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The first name cannot be empty!", response.getBody());
    }

    /*
     * Test to successfully get a list of general users
     */
    @Test
    @Order(8)
    public void testGetAllGeneralUsers() {

        GeneralUserDto generalUser1 = createGeneralUser();
        GeneralUserDto generalUser2 = createSecondGeneralUser();
        ResponseEntity<MultipleGeneralUserDto> response = client.getForEntity("/generalUsers",
                MultipleGeneralUserDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response);
        assertEquals(generalUser1.getFirstName(), response.getBody().getGeneralUserList().get(0).getFirstName());
        assertEquals(generalUser1.getLastName(), response.getBody().getGeneralUserList().get(0).getLastName());
        assertEquals(generalUser1.getEmail(), response.getBody().getGeneralUserList().get(0).getEmail());
        assertEquals(generalUser1.getPhoneNumber(), response.getBody().getGeneralUserList().get(0).getPhoneNumber());
        assertEquals(generalUser2.getFirstName(), response.getBody().getGeneralUserList().get(1).getFirstName());
        assertEquals(generalUser2.getLastName(), response.getBody().getGeneralUserList().get(1).getLastName());
        assertEquals(generalUser2.getEmail(), response.getBody().getGeneralUserList().get(1).getEmail());
        assertEquals(generalUser2.getPhoneNumber(), response.getBody().getGeneralUserList().get(1).getPhoneNumber());
    }

    /*
     * Helper function to create a general user
     */
    public GeneralUserDto createGeneralUser() {
        String firstName = "Joe";
        String lastName = "Doe";
        String email = "joe@gmail.com";
        long phoneNumber = 1234567891;
        GeneralUserDto generalUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);
        ResponseEntity<GeneralUserDto> response = client.postForEntity("/generalUsers/create", generalUserDto,
                GeneralUserDto.class);
        return response.getBody();
    }

    /*
     * Helper Fuction to create a second general user
     */
    public GeneralUserDto createSecondGeneralUser() {
        String firstName = "Joey";
        String lastName = "Doey";
        String email = "joey@gmail.com";
        long phoneNumber = 1234567891;
        GeneralUserDto generalUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);
        ResponseEntity<GeneralUserDto> response = client.postForEntity("/generalUsers/create", generalUserDto,
                GeneralUserDto.class);
        return response.getBody();
    }

}
