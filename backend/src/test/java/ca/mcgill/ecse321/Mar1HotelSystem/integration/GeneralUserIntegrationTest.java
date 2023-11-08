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
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemExceptionHandler;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.GeneralUserDto;

// import ca.mcgill.ecse321.eventregistration.dto.ErrorDto;
// import ca.mcgill.ecse321.eventregistration.dto.PersonRequestDto;
// import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
// import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;
// import ca.mcgill.ecse321.eventregistration.repository.RegistrationRepository;

// Start the app for real so that we can send requests to it, but use a random port to avoid conflicts.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// Reuse the same class for all the tests (instead of creating a new class each time).
@TestInstance(Lifecycle.PER_CLASS)
// Ensure the tests are run in the right order (e.g., POST before GET)
@TestMethodOrder(OrderAnnotation.class)
public class GeneralUserIntegrationTest {

	// Stores state to be shared between tests
	private class TestFixture {
		public static final int INVALID_ID = Integer.MAX_VALUE;

		public static final String PERSON_EMAIL = "joe@gmail.com";

		private String firstName;
		private String lastName;
		private String email; 
		private long phoneNumber;
		private String password;
		

		// Getters
		// Method to get firstName, returns firstName
		public String getFirstName() {
			return firstName;
		}

		// Method to get lastName, returns lastName
		public String getLastName() {
			return lastName;
		}

		// Method to get email, returns email
		public String getEmail() {
			return email;
		}

		// Method to get phoneNumber, returns phoneNumber
		public long getPhoneNumber() {
			return phoneNumber;
		}


		

		// Setters
		// Method to set firstName, returns true if firstName set
		public boolean setFirstName(String firstName) {
			this.firstName = firstName;
			return true;
		}

		// Method to set lastName, returns true if lastName set
		public boolean setLastName(String lastName) {
			this.lastName = lastName;
			return true;
		}

		// Method to set email, returns true if email set
		public boolean setEmail(String email) {
			this.email = email;
			return true;
		}

		// Method to set phoneNumber, returns true if phoneNumber set
		public boolean setPhoneNumber(long phoneNumber) {
			this.phoneNumber = phoneNumber;
			return true;
		}

		

	}

	private TestFixture fixture;
	@Autowired
	private GeneralUserRepository GeneralUserRepo;

	@Autowired
	private TestRestTemplate client;

	@BeforeAll
	public void setupTestFixture() {
		this.fixture = new TestFixture();
	}

	@BeforeAll
	@AfterAll
	public void clearDatabase() {
		GeneralUserRepo.deleteAll();
	}

	@Test
	@Order(0)
	public void testGetGeneralUsersInvalid() {

		ResponseEntity<GeneralUserDto> response = client.getForEntity("/GeneralUsers", GeneralUserDto.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		// assertEquals(1, response.getBody().getMessages().size());
		// assertEquals("There are no GeneralUsers Found", response.getBody().getMessages().get(0));
	}

	@Test
	@Order(1)
	public void testCreateGeneralUserValid() {
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		int phoneNumber = 1234567891;
		GeneralUserDto GeneralUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);

		ResponseEntity<GeneralUserDto> response = client.postForEntity("/GeneralUsers/email/firstName/lastName/phoneNumber"+email+firstName+lastName+phoneNumber, GeneralUserDto, GeneralUserDto.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(firstName, response.getBody().getFirstName());
		assertEquals(lastName, response.getBody().getLastName());
		assertEquals(email, response.getBody().getEmail());
		assertEquals(phoneNumber, response.getBody().getPhoneNumber());
		
		fixture.setFirstName(firstName);
		fixture.setLastName(lastName);
		fixture.setEmail(email);
		fixture.setPhoneNumber(phoneNumber);
		
	}

	@Test
	@Order(2)
	public void testGetSpecificGeneralUserValid() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();
		

		ResponseEntity<GeneralUserDto> response = client.getForEntity("/GeneralUsers/email"+email, GeneralUserDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(firstName, response.getBody().getFirstName());
		assertEquals(lastName, response.getBody().getLastName());
		assertEquals(email, response.getBody().getEmail());
		assertEquals(phoneNumber, response.getBody().getPhoneNumber());
		
	}

	@Test
	@Order(3)
	public void testGetSpecificGeneralUserInvalid() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();
		ResponseEntity<GeneralUserDto> response = client.getForEntity("/GeneralUsers/email"+email, GeneralUserDto.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		// assertEquals(1, response.getBody().getMessages().size());
		// assertEquals("GeneralUser Not Found", response.getBody().getMessages().get(0));
		
	}

	@Test
	@Order(4)
	public void testCreateSecondGeneralUserInavlid1() {
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		int phoneNumber = 1234567891;
		
		GeneralUserDto GeneralUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);

		ResponseEntity<GeneralUserDto> response = client.postForEntity("/GeneralUsers/email/firstName/lastName/phoneNumber"+email+firstName+lastName+phoneNumber, GeneralUserDto, GeneralUserDto.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		// assertEquals(1, response.getBody().getMessages().size());
		// assertContains("User with that email already exists!", response.getBody().getMessages());
	}

	@Test
	@Order(5)
	public void testCreateSecondGeneralUserValid() {
		String firstName = "Joey";
		String lastName = "Doey";
		String email = "joey@gmail.com";
		int phoneNumber = 1234567891;
		GeneralUserDto GeneralUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);

		ResponseEntity<GeneralUserDto> response = client.postForEntity("/GeneralUsers/email/firstName/lastName/phoneNumber"+email+firstName+lastName+phoneNumber, GeneralUserDto, GeneralUserDto.class);

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
		// fixture.setPassword(password);
	}

	@Test
	@Order(6)
	public void testGetGeneralUsersList() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();

		ResponseEntity<GeneralUserDto> response = client.getForEntity("/GeneralUsers", GeneralUserDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(firstName, response.getBody().getFirstName());
		assertEquals(lastName, response.getBody().getLastName());
		assertEquals(email, response.getBody().getEmail());
		assertEquals(phoneNumber, response.getBody().getPhoneNumber());
	}

	@Test
	@Order(7)
	public void testUpdateGeneralUserValid() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();
	
		
		ResponseEntity<GeneralUserDto> GeneralUser = client.getForEntity("/GeneralUsers/email"+email, GeneralUserDto.class);
		ResponseEntity<GeneralUserDto> response = client.postForEntity("/GeneralUsers/oldEmail/newEmail"+email+"hey@gmail.com", GeneralUser, GeneralUserDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(firstName, response.getBody().getFirstName());
		assertEquals(lastName, response.getBody().getLastName());
		assertEquals(email, response.getBody().getEmail());
		assertEquals(phoneNumber, response.getBody().getPhoneNumber());
	}

	@Test
	@Order(8)
	public void testUpdateGeneralUserInvalid1() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();

		ResponseEntity<GeneralUserDto> GeneralUser = client.getForEntity("/GeneralUsers/email"+email, GeneralUserDto.class);
		ResponseEntity<GeneralUserDto> response = client.postForEntity("/GeneralUsers/oldEmail/newEmail"+email+email, GeneralUser, GeneralUserDto.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		// assertEquals(1, response.getBody().getMessages().size());
		// assertContains("User with that email already exists!", response.getBody().getMessages());
	}

	@Test
	@Order(9)
	public void testUpdateGeneralUserInvalid2() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();

		ResponseEntity<GeneralUserDto> GeneralUser = client.getForEntity("/GeneralUsers/email"+email, GeneralUserDto.class);
		ResponseEntity<GeneralUserDto> response = client.postForEntity("/GeneralUsers/oldEmail/newEmail"+"heyy@gmail.com"+"heyo@gmail.com", GeneralUser, GeneralUserDto.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		// assertEquals(1, response.getBody().getMessages().size());
		// assertContains("User Not Found", response.getBody().getMessages());
	}

    @Test
	@Order(10)
	public void testUpdateGeneralUserInvalid3() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();

		ResponseEntity<GeneralUserDto> GeneralUser = client.getForEntity("/GeneralUsers/email"+email, GeneralUserDto.class);
		ResponseEntity<GeneralUserDto> response = client.postForEntity("/GeneralUsers/oldEmail/newEmail"+email+"joe@gmailcom", GeneralUser, GeneralUserDto.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		// assertEquals(1, response.getBody().getMessages().size());
		// assertContains("The new email is invalid!", response.getBody().getMessages());
	}

	@Test
	@Order(11)
	public void testDeleteGeneralUserValid() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();
		ResponseEntity<GeneralUserDto> GeneralUser = client.getForEntity("/GeneralUsers/email"+email, GeneralUserDto.class);
		ResponseEntity<GeneralUserDto> response = client.postForEntity("/GeneralUsers/email"+email, GeneralUser, GeneralUserDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}

	@Test
	@Order(12)
	public void testDeleteGeneralUserInvalid() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();
		ResponseEntity<GeneralUserDto> GeneralUser = client.getForEntity("/GeneralUsers/email"+email, GeneralUserDto.class);
		ResponseEntity<GeneralUserDto> response = client.postForEntity("/GeneralUsers/email"+email, GeneralUser, GeneralUserDto.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		// assertEquals(1, response.getBody().getMessages().size());
		// assertContains("User with that email does not exist!", response.getBody().getMessages());
	}

	@Test
	@Order(13)
	public void testCreateGeneralUserInvalid2() {
		String firstName = "";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		int phoneNumber = 1234567891;
		GeneralUserDto GeneralUserDto = new GeneralUserDto(firstName, lastName, email, phoneNumber);

		ResponseEntity<GeneralUserDto> response = client.postForEntity("/GeneralUsers/email/firstName/lastName/phoneNumber"+email+firstName+lastName+phoneNumber, GeneralUserDto, GeneralUserDto.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		// assertEquals(1, response.getBody().getMessages().size());
		// assertContains("The first name cannot be empty!", response.getBody().getMessages());
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

