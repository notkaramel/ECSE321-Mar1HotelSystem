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
import ca.mcgill.ecse321.Mar1HotelSystem.dao.ManagerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.ManagerDto;

// Start the app for real so that we can send requests to it, but use a random port to avoid conflicts.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// // Reuse the same class for all the tests (instead of creating a new class
// each time).
@TestInstance(Lifecycle.PER_CLASS)

public class ManagerIntegrationTest {

	@Autowired
	private ManagerRepository ManagerRepo;

	@Autowired
	private TestRestTemplate client;

	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		ManagerRepo.deleteAll();
	}

	@Test
	@Order(0)
	public void testGetManagersInvalid() {
		ResponseEntity<String> response = client.getForEntity("/managers", String.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("There are no Managers Found", response.getBody());
	}

	@Test
	@Order(1)
	public void testCreateManagerValid() {
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		long phoneNumber = 1234567891;
		String password = "pass";
		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);
		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/create", managerDto, ManagerDto.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(firstName, response.getBody().getFirstName());
		assertEquals(lastName, response.getBody().getLastName());
		assertEquals(email, response.getBody().getEmail());
		assertEquals(phoneNumber, response.getBody().getPhoneNumber());
		assertEquals(password, response.getBody().getPassword());

	}

	public ManagerDto createManager() {
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		long phoneNumber = 1234567891;
		String password = "pass";
		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);
		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/create", managerDto, ManagerDto.class);
		return response.getBody();
	}

	@Test
	@Order(2)
	public void testGetSpecificManagerValid() {

		ManagerDto user = createManager();

		ResponseEntity<ManagerDto> response = client.getForEntity("/managers/" + "joe@gmail.com", ManagerDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(user.getFirstName(), response.getBody().getFirstName());
		assertEquals(user.getLastName(), response.getBody().getLastName());
		assertEquals(user.getEmail(), response.getBody().getEmail());
		assertEquals(user.getPhoneNumber(), response.getBody().getPhoneNumber());
		assertEquals(user.getPassword(), response.getBody().getPassword());

	}

	@Test
	@Order(3)
	public void testGetSpecificManagerInvalid() {
		ManagerDto user = createManager();
		ResponseEntity<String> response = client.getForEntity("/managers/" + "hey@gmail.com", String.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Manager Not Found", response.getBody());

	}

	@Test
	@Order(4)
	public void testCreateSecondManagerInavlid1() {
		ManagerDto user = createManager();
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		long phoneNumber = 1234567891;
		String password = "pass";

		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);

		ResponseEntity<String> response = client.postForEntity("/managers/create", managerDto, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("User with that email already exists!", response.getBody());
	}

	@Test
	@Order(5)
	public void testCreateSecondManagerValid() {
		ManagerDto user = createManager();
		String firstName = "Joey";
		String lastName = "Doey";
		String email = "joey@gmail.com";
		long phoneNumber = 1234567891;
		String password = "pass";
		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);

		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/create", managerDto, ManagerDto.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(firstName, response.getBody().getFirstName());
		assertEquals(lastName, response.getBody().getLastName());
		assertEquals(email, response.getBody().getEmail());
		assertEquals(phoneNumber, response.getBody().getPhoneNumber());
		assertEquals(user.getPassword(), response.getBody().getPassword());
	}

	public ManagerDto createSecondManager() {
		String firstName = "Joey";
		String lastName = "Doey";
		String email = "joey@gmail.com";
		long phoneNumber = 1234567891;
		String password = "pass";
		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);
		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/create", managerDto, ManagerDto.class);
		return response.getBody();
	}

	@Test
	@Order(6)
	public void testDeleteManagerValid() {
		ManagerDto manager = createManager();
		ResponseEntity<Boolean> response = client.postForEntity("/managers/delete", manager, Boolean.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	@Order(7)
	public void testCreateManagerInvalid2() {
		String firstName = "";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		long phoneNumber = 1234567891;
		String password = "pass";
		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);

		ResponseEntity<String> response = client.postForEntity("/managers/create", managerDto, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("The first name cannot be empty!", response.getBody());
	}

}
