package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemExceptionHandler;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.service.GeneralUserService;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.GeneralUserDto;

// import ca.mcgill.ecse321.eventregistration.dto.ErrorDto;
// import ca.mcgill.ecse321.eventregistration.dto.PersonRequestDto;
// import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
// import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;
// import ca.mcgill.ecse321.eventregistration.repository.RegistrationRepository;

// Start the app for real so that we can send requests to it, but use a random port to avoid conflicts.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// // Reuse the same class for all the tests (instead of creating a new class each time).
@TestInstance(Lifecycle.PER_CLASS)
// // Ensure the tests are run in the right order (e.g., POST before GET)
//@TestMethodOrder(OrderAnnotation.class)
public class GeneralUserIntegrationTest {

	// // Stores state to be shared between tests
	// private class TestFixture {
	// 	public static final int INVALID_ID = Integer.MAX_VALUE;

	// 	public static final String PERSON_EMAIL = "joe@gmail.com";

	// 	private String firstName;
	// 	private String lastName;
	// 	private String email; 
	// 	private long phoneNumber;
	// 	private String password;
		

	// 	// Getters
	// 	// Method to get firstName, returns firstName
	// 	public String getFirstName() {
	// 		return firstName;
	// 	}

	// 	// Method to get lastName, returns lastName
	// 	public String getLastName() {
	// 		return lastName;
	// 	}

	// 	// Method to get email, returns email
	// 	public String getEmail() {
	// 		return email;
	// 	}

	// 	// Method to get phoneNumber, returns phoneNumber
	// 	public long getPhoneNumber() {
	// 		return phoneNumber;
	// 	}


		

	// 	// Setters
	// 	// Method to set firstName, returns true if firstName set
	// 	public boolean setFirstName(String firstName) {
	// 		this.firstName = firstName;
	// 		return true;
	// 	}

	// 	// Method to set lastName, returns true if lastName set
	// 	public boolean setLastName(String lastName) {
	// 		this.lastName = lastName;
	// 		return true;
	// 	}

	// 	// Method to set email, returns true if email set
	// 	public boolean setEmail(String email) {
	// 		this.email = email;
	// 		return true;
	// 	}

	// 	// Method to set phoneNumber, returns true if phoneNumber set
	// 	public boolean setPhoneNumber(long phoneNumber) {
	// 		this.phoneNumber = phoneNumber;
	// 		return true;
	// 	}

		

	// }

	// private TestFixture fixture;
	@Autowired
	private GeneralUserRepository GeneralUserRepo;

	@Autowired
	private GeneralUserService generalUserService;

	@Autowired
	private TestRestTemplate client;

	// @BeforeAll
	// public void setupTestFixture() {
	// 	this.fixture = new TestFixture();
	// }

	@BeforeAll
	@AfterAll
	public void clearDatabase() {
		GeneralUserRepo.deleteAll();
	}

	@Test
	@DirtiesContext
	@Order(0)
	public void testGetGeneralUsersInvalid() {
		ResponseEntity<String> response = client.getForEntity("/generalUsers", String.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("There are no Users found!", response.getBody());
	}

	@Test
	@DirtiesContext
	@Order(1)
	public void testCreateGeneralUserValid() {
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		int phoneNumber = 1234567891;
		GeneralUserDto generalUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);
		ResponseEntity<GeneralUserDto> response = client.postForEntity("/generalUsers/create", generalUserDto, GeneralUserDto.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(firstName, response.getBody().getFirstName());
		assertEquals(lastName, response.getBody().getLastName());
		assertEquals(email, response.getBody().getEmail());
		assertEquals(phoneNumber, response.getBody().getPhoneNumber());
		
		// fixture.setFirstName(firstName);
		// fixture.setLastName(lastName);
		// fixture.setEmail(email);
		// fixture.setPhoneNumber(phoneNumber);
		
	}

	
	public GeneralUserDto createGeneralUser(){
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		int phoneNumber = 1234567891;
		GeneralUserDto generalUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);
		ResponseEntity<GeneralUserDto> response = client.postForEntity("/generalUsers/create", generalUserDto, GeneralUserDto.class);
		return response.getBody();
	}
	@Test
	@DirtiesContext
	@Order(3)
	public void testGetSpecificGeneralUserValid() {
		
		GeneralUserDto user = createGeneralUser();

		ResponseEntity<GeneralUserDto> response = client.getForEntity("/generalUsers/"+"joe@gmail.com", GeneralUserDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(user.getFirstName(), response.getBody().getFirstName());
		assertEquals(user.getLastName(), response.getBody().getLastName());
		assertEquals(user.getEmail(), response.getBody().getEmail());
		assertEquals(user.getPhoneNumber(), response.getBody().getPhoneNumber());
		
	}

	@Test
	@DirtiesContext
	@Order(4)
	public void testGetSpecificGeneralUserInvalid() {
		GeneralUserDto user = createGeneralUser();
		ResponseEntity<String> response = client.getForEntity("/generalUsers/"+"hey@gmail.com", String.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("User Not Found", response.getBody());
		
	}

	@Test
	@DirtiesContext
	@Order(5)
	public void testCreateSecondGeneralUserInavlid1() {
		GeneralUserDto user = createGeneralUser();
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		int phoneNumber = 1234567891;
		
		GeneralUserDto generalUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);

		ResponseEntity<String> response = client.postForEntity("/generalUsers/create", generalUserDto, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		//assertEquals(1, response.getBody().getMessages().size());
		assertEquals("User with that email already exists!", response.getBody());
	}

	@Test
	@DirtiesContext
	@Order(6)
	public void testCreateSecondGeneralUserValid() {
		GeneralUserDto user = createGeneralUser();
		String firstName = "Joey";
		String lastName = "Doey";
		String email = "joey@gmail.com";
		int phoneNumber = 1234567891;
		GeneralUserDto generalUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);

		ResponseEntity<GeneralUserDto> response = client.postForEntity("/generalUsers/create", generalUserDto, GeneralUserDto.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(firstName, response.getBody().getFirstName());
		assertEquals(lastName, response.getBody().getLastName());
		assertEquals(email, response.getBody().getEmail());
		assertEquals(phoneNumber, response.getBody().getPhoneNumber());
	}

	public GeneralUserDto createSecondGeneralUser(){
		String firstName = "Joey";
		String lastName = "Doey";
		String email = "joey@gmail.com";
		int phoneNumber = 1234567891;
		GeneralUserDto generalUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);
		ResponseEntity<GeneralUserDto> response = client.postForEntity("/generalUsers/create", generalUserDto, GeneralUserDto.class);
		return response.getBody();
	}
	// @Test
	// @Order(6)
	// public void testGetGeneralUsersList() {
	// 	GeneralUserDto user1 = createGeneralUser();
	// 	GeneralUserDto user2 = createSecondGeneralUser();
		

	// 	// ResponseEntity<GeneralUserDto> response = client.getForEntity("/generalUsers", GeneralUserDto.class);
	// 	Iterable<GeneralUserDto> response = client.getForEntity("/generalUsers", GeneralUserDto.class);
		
	// 	assertEquals(HttpStatus.OK, response.getStatusCode());
	// 	assertNotNull(response.getBody());
	// 	// assertEquals(firstName, response.getBody().getFirstName());
	// 	// assertEquals(lastName, response.getBody().getLastName());
	// 	// assertEquals(email, response.getBody().getEmail());
	// 	// assertEquals(phoneNumber, response.getBody().getPhoneNumber());
	// }

	// @Test
	// @Order(7)
	// public void testUpdateGeneralUserValid() {
	// 	GeneralUserDto generalUser = createGeneralUser();
	// 	ResponseEntity<GeneralUserDto> response = client.postForEntity("/generalUsers/hey@gmail.com", generalUser, GeneralUserDto.class);

	// 	assertEquals(HttpStatus.OK, response.getStatusCode());
	// 	assertNotNull(response.getBody());
	// 	assertEquals(generalUser.getFirstName(), response.getBody().getFirstName());
	// 	assertEquals(generalUser.getLastName(), response.getBody().getLastName());
	// 	assertEquals(generalUser.getEmail(), response.getBody().getEmail());
	// 	assertEquals(generalUser.getPhoneNumber(), response.getBody().getPhoneNumber());
	// }

	// @Test
	// @Order(8)
	// public void testUpdateGeneralUserInvalid1() {	
	// 	String email = fixture.getEmail();

	// 	ResponseEntity<GeneralUserDto> generalUser = client.getForEntity("/generalUsers/email"+email, GeneralUserDto.class);
	// 	ResponseEntity<GeneralUserDto> response = client.postForEntity("/generalUsers/newEmail"+email, generalUser, GeneralUserDto.class);

	// 	assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	// 	// assertEquals(1, response.getBody().getMessages().size());
	// 	// assertContains("User with that email already exists!", response.getBody().getMessages());
	// }

	// @Test
	// @Order(9)
	// public void testUpdateGeneralUserInvalid2() {
	// 	String firstName = fixture.getFirstName();
	// 	String lastName = fixture.getLastName();
	// 	String email = fixture.getEmail();
	// 	long phoneNumber = fixture.getPhoneNumber();

	// 	ResponseEntity<GeneralUserDto> GeneralUser = client.getForEntity("/generalUsers/email"+email, GeneralUserDto.class);
	// 	ResponseEntity<GeneralUserDto> response = client.postForEntity("/generalUsers/newEmail"+"heyy@gmail.com"+"heyo@gmail.com", GeneralUser, GeneralUserDto.class);

	// 	assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	// 	// assertEquals(1, response.getBody().getMessages().size());
	// 	// assertContains("User Not Found", response.getBody().getMessages());
	// }

    // @Test
	// @Order(9)
	// public void testUpdateGeneralUserInvalid3() {
	// 	String email = fixture.getEmail();

	// 	ResponseEntity<GeneralUserDto> generalUser = client.getForEntity("/generalUsers/email"+email, GeneralUserDto.class);
	// 	ResponseEntity<GeneralUserDto> response = client.postForEntity("/generalUsers/newEmail"+"joe@gmailcom", generalUser, GeneralUserDto.class);

	// 	assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	// 	// assertEquals(1, response.getBody().getMessages().size());
	// 	// assertContains("The new email is invalid!", response.getBody().getMessages());
	// }

	@Test
	public void testDeleteGeneralUserValid() {
		GeneralUserDto generalUser = createGeneralUser();
		ResponseEntity<Boolean> response = client.postForEntity("/generalUsers/delete", generalUser, Boolean.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}

	// @Test
	// @Order(11)
	// public void testDeleteGeneralUserInvalid() {
	// 	ResponseEntity<String> response = client.postForEntity("/generalUsers/delete", generalUser, String.class);

	// 	assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	//  	assertEquals("User with that email does not exist!", response.getBody());
	// }

	@Test
	@DirtiesContext
	@Order(7)
	public void testCreateGeneralUserInvalid2() {
		String firstName = "";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		int phoneNumber = 1234567891;
		GeneralUserDto generalUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);

		ResponseEntity<String> response = client.postForEntity("/generalUsers/create", generalUserDto, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		// assertEquals(1, response.getBody().getMessages().size());
		assertEquals("The first name cannot be empty!", response.getBody());
	}


	private static void assertContains(String expected, List<String> actual) {
		for (String s : actual) {
			if (expected.equals(s)) {
				return;
			}
		}
		fail(String.format("Error message '%s' not found in list."));
	}

}

