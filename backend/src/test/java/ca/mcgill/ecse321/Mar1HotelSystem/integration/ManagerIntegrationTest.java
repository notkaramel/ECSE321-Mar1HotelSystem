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
import ca.mcgill.ecse321.Mar1HotelSystem.dao.ManagerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.ManagerDto;

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
public class ManagerIntegrationTest {

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


		// Method to get password, returns password
		public String getPassword() {
			return password;
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

		// Method to set password, returns true if password successfully set
		public boolean setPassword(String password) {
			this.password = password;
			return true;
		}

	}

	private TestFixture fixture;
	@Autowired
	private ManagerRepository managerRepo;

	@Autowired
	private TestRestTemplate client;

	@BeforeAll
	public void setupTestFixture() {
		this.fixture = new TestFixture();
	}

	@BeforeAll
	@AfterAll
	public void clearDatabase() {
		managerRepo.deleteAll();
	}

	@Test
	@Order(0)
	public void testGetManagersInvalid() {

		ResponseEntity<ManagerDto> response = client.getForEntity("/managers", ManagerDto.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		// assertEquals(1, response.getBody().getMessages().size());
		// assertEquals("There are no Managers Found", response.getBody().getMessages().get(0));
	}

	@Test
	@Order(1)
	public void testCreateManagerValid() {
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		int phoneNumber = 1234567891;
		String password = "worked";
		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);

		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/email/firstName/lastName/phoneNumber/password"+email+firstName+lastName+phoneNumber+password, managerDto, ManagerDto.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(firstName, response.getBody().getFirstName());
		assertEquals(lastName, response.getBody().getLastName());
		assertEquals(email, response.getBody().getEmail());
		assertEquals(phoneNumber, response.getBody().getPhoneNumber());
		assertEquals(password, response.getBody().getPassword());
		fixture.setFirstName(firstName);
		fixture.setLastName(lastName);
		fixture.setEmail(email);
		fixture.setPhoneNumber(phoneNumber);
		fixture.setPassword(password);
	}

	@Test
	@Order(2)
	public void testGetSpecificManagerValid() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();
		String password = fixture.getPassword();

		ResponseEntity<ManagerDto> response = client.getForEntity("/managers/email"+email, ManagerDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(firstName, response.getBody().getFirstName());
		assertEquals(lastName, response.getBody().getLastName());
		assertEquals(email, response.getBody().getEmail());
		assertEquals(phoneNumber, response.getBody().getPhoneNumber());
		assertEquals(password, response.getBody().getPassword());
	}

	@Test
	@Order(3)
	public void testGetSpecificManagerInvalid() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();
		String password = fixture.getPassword();

		ResponseEntity<ManagerDto> response = client.getForEntity("/managers/email"+email, ManagerDto.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		// assertEquals(1, response.getBody().getMessages().size());
		// assertEquals("Manager Not Found", response.getBody().getMessages().get(0));
		
	}

	@Test
	@Order(4)
	public void testCreateSecondManagerInavlid1() {
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		int phoneNumber = 1234567891;
		String password = "worked";
		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);

		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/email/firstName/lastName/phoneNumber/password"+email+firstName+lastName+phoneNumber+password, managerDto, ManagerDto.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		// assertEquals(1, response.getBody().getMessages().size());
		// assertContains("User with that email already exists!", response.getBody().getMessages());
	}

	@Test
	@Order(5)
	public void testCreateSecondManagerValid() {
		String firstName = "Joey";
		String lastName = "Doey";
		String email = "joey@gmail.com";
		int phoneNumber = 1234567891;
		String password = "worked";
		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);

		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/email/firstName/lastName/phoneNumber/password"+email+firstName+lastName+phoneNumber+password, managerDto, ManagerDto.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(firstName, response.getBody().getFirstName());
		assertEquals(lastName, response.getBody().getLastName());
		assertEquals(email, response.getBody().getEmail());
		assertEquals(phoneNumber, response.getBody().getPhoneNumber());
		assertEquals(password, response.getBody().getPassword());
		// fixture.setFirstName(firstName);
		// fixture.setLastName(lastName);
		// fixture.setEmail(email);
		// fixture.setPhoneNumber(phoneNumber);
		// fixture.setPassword(password);
	}

	@Test
	@Order(6)
	public void testGetManagersList() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();
		String password = fixture.getPassword();

		ResponseEntity<ManagerDto> response = client.getForEntity("/managers", ManagerDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(firstName, response.getBody().getFirstName());
		assertEquals(lastName, response.getBody().getLastName());
		assertEquals(email, response.getBody().getEmail());
		assertEquals(phoneNumber, response.getBody().getPhoneNumber());
		assertEquals(password, response.getBody().getPassword());
	}

	@Test
	@Order(7)
	public void testUpdateManagerValid() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();
		String password = fixture.getPassword();
		
		ResponseEntity<ManagerDto> manager = client.getForEntity("/managers/email"+email, ManagerDto.class);
		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/email/oldPassword/newPassword"+email+password+"yayy", manager, ManagerDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(firstName, response.getBody().getFirstName());
		assertEquals(lastName, response.getBody().getLastName());
		assertEquals(email, response.getBody().getEmail());
		assertEquals(phoneNumber, response.getBody().getPhoneNumber());
		assertEquals(password, response.getBody().getPassword());
	}

	@Test
	@Order(8)
	public void testUpdateManagerInvalid1() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();
		String password = fixture.getPassword();

		ResponseEntity<ManagerDto> manager = client.getForEntity("/managers/email"+email, ManagerDto.class);
		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/email/oldPassword/newPassword"+email+"heyy"+"yayy", manager, ManagerDto.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		// assertEquals(1, response.getBody().getMessages().size());
		// assertContains("Incorrect old password!", response.getBody().getMessages());
	}

	@Test
	@Order(9)
	public void testUpdateManagerInvalid2() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();
		String password = fixture.getPassword();

		ResponseEntity<ManagerDto> manager = client.getForEntity("/managers/email"+email, ManagerDto.class);
		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/email/oldPassword/newPassword"+"heyy@gmail.com"+password+"yayy", manager, ManagerDto.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		// assertEquals(1, response.getBody().getMessages().size());
		// assertContains("User Not Found", response.getBody().getMessages());
	}

	@Test
	@Order(10)
	public void testDeleteManagerValid() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();
		String password = fixture.getPassword();
		ResponseEntity<ManagerDto> manager = client.getForEntity("/managers/email"+email, ManagerDto.class);
		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/email"+email, manager, ManagerDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}

	@Test
	@Order(11)
	public void testDeleteManagerInvalid() {
		String firstName = fixture.getFirstName();
		String lastName = fixture.getLastName();
		String email = fixture.getEmail();
		long phoneNumber = fixture.getPhoneNumber();
		String password = fixture.getPassword();
		ResponseEntity<ManagerDto> manager = client.getForEntity("/managers/email"+email, ManagerDto.class);
		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/email"+email, manager, ManagerDto.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		// assertEquals(1, response.getBody().getMessages().size());
		// assertContains("User with that email does not exist!", response.getBody().getMessages());
	}

	@Test
	@Order(12)
	public void testCreateManagerInvalid2() {
		String firstName = "";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		int phoneNumber = 1234567891;
		String password = "worked";
		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);

		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/email/firstName/lastName/phoneNumber/password"+email+firstName+lastName+phoneNumber+password, managerDto, ManagerDto.class);

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

