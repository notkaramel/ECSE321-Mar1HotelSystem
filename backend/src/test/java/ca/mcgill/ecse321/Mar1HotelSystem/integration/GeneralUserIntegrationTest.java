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

// Start the app for real so that we can send requests to it, but use a random port to avoid conflicts.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// // Reuse the same class for all the tests (instead of creating a new class
// each time).
@TestInstance(Lifecycle.PER_CLASS)
public class GeneralUserIntegrationTest {

	@Autowired
	private GeneralUserRepository GeneralUserRepo;

	@Autowired
	private TestRestTemplate client;

	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		GeneralUserRepo.deleteAll();
	}

	@Test
	@Order(0)
	public void testGetGeneralUsersInvalid() {
		ResponseEntity<String> response = client.getForEntity("/generalUsers", String.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("There are no Users found!", response.getBody());
	}

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

	@Test
	@Order(3)
	public void testGetSpecificGeneralUserInvalid() {
		GeneralUserDto user = createGeneralUser();
		ResponseEntity<String> response = client.getForEntity("/generalUsers/" + "hey@gmail.com", String.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("User Not Found", response.getBody());

	}

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

	@Test
	@Order(6)
	public void testDeleteGeneralUserValid() {
		GeneralUserDto generalUser = createGeneralUser();
		ResponseEntity<Boolean> response = client.postForEntity("/generalUsers/delete", generalUser, Boolean.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

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

}
