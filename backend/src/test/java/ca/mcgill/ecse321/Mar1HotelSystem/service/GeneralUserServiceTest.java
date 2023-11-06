package ca.mcgill.ecse321.Mar1HotelSystem.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class GeneralUserServiceTest {

	@Mock
	private GeneralUserRepository generalUserDao;

	@InjectMocks
	private GeneralUserService generalUserService;

	private static final String GENERALUSER_KEY = "joe@gmail.com";

	@BeforeEach
	public void setMockOutput() {
		lenient().when(generalUserDao.findGeneralUserByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(GENERALUSER_KEY)) {
				GeneralUser generalUser = new GeneralUser();
				generalUser.setEmail(GENERALUSER_KEY);
				return generalUser;
			} else {
				return null;
			}
		});
		lenient().when(generalUserDao.findAll()).thenAnswer((invocation) -> {
			List<GeneralUser> generalUsers = new ArrayList<>();
			GeneralUser generalUser1 = new GeneralUser();
			generalUser1.setEmail("joey@gmail.com");
			GeneralUser generalUser2 = new GeneralUser();
			generalUser2.setEmail("janet@gmail.com");
			generalUsers.add(generalUser1);
			generalUsers.add(generalUser2);
			return generalUsers;
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(generalUserDao.save(any(GeneralUser.class))).thenAnswer(returnParameterAsAnswer);
	}

	/*
	 * Create a new General User successfully
	 */
	@Test
	public void testCreateGeneralUser() {
		String firstName = "Joeye";
		String lastName = "Doey";
		String email = "joeye@gmail.com";
		int phoneNumber = 1234567891;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(generalUser);
		assertEquals(email, generalUser.getEmail());
		

	}

	/*
	 * Fail to create general user if a general user is trying to be created with
	 * the same email as another user
	 */
	@Test
	public void testCreateGeneralUserTwice() {
		String error = null;
		String firstName = "Joe";
		String lastName = "Doe";
		String email = GENERALUSER_KEY;
		int phoneNumber = 1234567891;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);

		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(generalUser);
		assertEquals("User with that email already exists!", error);

	}

	/*
	 * Fail to create a general user if all inputs null
	 */
	@Test
	public void testCreateGeneralUserNull() {
		String error = null;
		String firstName = null;
		String lastName = null;
		String email = null;
		int phoneNumber = 0;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(generalUser);
		assertEquals("All inputs null!", error);
	}

	/*
	 * Fail to create a general user if first name inputs null
	 */
	@Test
	public void testCreateGeneralUserAllSpace() {
		String error = null;
		String firstName = "";
		String lastName = "";
		String email = "";
		int phoneNumber = 0;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);
		} catch (IllegalArgumentException e) {

			error = e.getMessage();
		}
		assertNull(generalUser);
		assertEquals("All fields are empty!", error);
	}

	/*
	 * Fail to create a general user if first name input empty
	 */
	@Test
	public void testCreateGeneralUserFistNameSpace() {
		String error = null;
		String firstName = "";
		String lastName = "Doe";
		String email = GENERALUSER_KEY;
		int phoneNumber = 1234567891;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(generalUser);
		assertEquals("The first name cannot be empty!", error);
	}

	/*
	 * Fail to create a general user if last name input empty
	 */
	@Test
	public void testCreateGeneralUserLastNameSpace() {
		String error = null;
		String firstName = "Joe";
		String lastName = "";
		String email = GENERALUSER_KEY;
		int phoneNumber = 1234567891;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(generalUser);
		assertEquals("The last name cannot be empty!", error);
	}

	/*
	 * Fail to create a general user if email input empty
	 */
	@Test
	public void testCreateGeneralUserEmailSpace() {
		String error = null;
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "";
		int phoneNumber = 1234567891;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(generalUser);
		assertEquals("The email cannot be empty!", error);
	}

	/*
	 * Fail to create a general user if email input missing @
	 */
	@Test
	public void testCreateGeneralUserEmailMissingAt() {
		String error = null;
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joegmail.com";
		int phoneNumber = 1234567891;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(generalUser);
		assertEquals("The email is invalid!", error);
	}

	/*
	 * Fail to create a general user if email input missing dot
	 */
	@Test
	public void testCreateGeneralUserEmailMissingDot() {
		String error = null;
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joe@gmailcom";
		int phoneNumber = 1234567891;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(generalUser);
		assertEquals("The email is invalid!", error);
	}

	/*
	 * Get general user from correct email of existing manager successfully
	 */
	@Test
	public void testGetGeneralUserSuccessful() {
		String error = null;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.getGeneralUser("joe@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNotNull(generalUser);
		assertEquals("joe@gmail.com", generalUser.getEmail());

	}

	/*
	 * Fail to get general user because email of non-existing manager
	 */
	@Test
	public void testGetGeneralUserUnsuccessful() {
		String error = null;
		GeneralUser generalUser = null;
		try {
			generalUserService.getGeneralUser("jane@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();

		}
		assertNull(generalUser);
		assertEquals("User Not Found", error);

	}

	/*
	 * Get list of general users successfully
	 */
	@Test
	public void testGetGeneralUserListSuccessful() {
		String error = null;
		List<GeneralUser> generalUsers = null;
		try {
			generalUsers = generalUserService.getAllGeneralUsers();
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNotNull(generalUsers);
		assertEquals("joey@gmail.com", generalUsers.get(0).getEmail());
		assertEquals("janet@gmail.com", generalUsers.get(1).getEmail());
	}

	/*
	 * Update general user email from correct email of existing general user
	 */
	@Test
	public void testUpdateGeneralUserThatExist() {
		String error = null;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.updateGeneralUserEmail("joe@gmail.com", "janette@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNotNull(generalUser);
		assertEquals("janette@gmail.com", generalUser.getEmail());

	}

	/*
	 * Fail to update general user email from correct email of existing general user
	 * but new email is invalid, missing @ in this example
	 */
	@Test
	public void testUpdateGeneralUserThatExistNewEmailIncorrect() {
		String error = null;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.updateGeneralUserEmail("joe@gmail.com", "janettegmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(generalUser);
		assertEquals("The new email is invalid!", error);

	}

	/*
	 * Fail to update general user email from incorrect email of non-existing
	 * general user
	 */
	@Test
	public void testUpdateGeneralUserThatDoesNotExist() {
		String error = null;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.updateGeneralUserEmail("Gio@gmail.com", "jane@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(generalUser);
		assertEquals("User Not Found", error);
	}

	/*
	 * Fail to update general user email from correct email of existing general user
	 * but new email is already associated to another general user
	 */
	@Test
	public void testUpdateGeneralUserThatAlreadyHasEmail() {
		String error = null;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.updateGeneralUserEmail("joe@gmail.com", "joe@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(generalUser);
		assertEquals("User with that email already exists!", error);
	}

	/*
	 * Delete general user from correct email of existing general user successfully
	 */
	@Test
	public void testDeleteGeneralUserThatExist() {
		String error = null;
		Boolean generalUser = null;
		try {
			generalUser = generalUserService.deleteGeneralUser("joe@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNotNull(generalUser);
		assertEquals(true, generalUser);
	}

	/*
	 * Unsuccessfully delete general user of non-existing general user
	 */
	@Test
	public void testDeleteGeneralUserThatDoesNotExist() {
		String error = null;
		Boolean generalUser = null;
		try {
			generalUser = generalUserService.deleteGeneralUser("George@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
			assertEquals("User with that email does not exist!", error);
		}
		assertEquals(false, generalUser);
	}

}
