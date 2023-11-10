package ca.mcgill.ecse321.Mar1HotelSystem.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
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
		setMockOutput();
		String firstName = "Joeye";
		String lastName = "Doey";
		String email = "joeye@gmail.com";
		long phoneNumber = 1234567891;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);
		} catch (Mar1HotelSystemException e) {
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
		setMockOutput();
		String error = null;
		HttpStatus error_status = null;
		String firstName = "Joe";
		String lastName = "Doe";
		String email = GENERALUSER_KEY;
		long phoneNumber = 1234567891;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);

		} catch (Mar1HotelSystemException e) {
			// Check that no error occurred
			error = e.getMessage();
			error_status = e.getStatus();
		}
		assertNull(generalUser);
		assertEquals("User with that email already exists!", error);
		assertEquals(HttpStatus.BAD_REQUEST, error_status);

	}

	/*
	 * Fail to create a general user if all inputs null
	 */
	@Test
	public void testCreateGeneralUserNull() {
		setMockOutput();
		String error = null;
		HttpStatus error_status = null;
		String firstName = null;
		String lastName = null;
		String email = null;
		long phoneNumber = 0;
		GeneralUser generalUser = null;
		try {
			generalUser = generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);
		} catch (Mar1HotelSystemException e) {
			error = e.getMessage();
			error_status = e.getStatus();
		}
		assertNull(generalUser);
		assertEquals("All inputs null!", error);
		assertEquals(HttpStatus.BAD_REQUEST, error_status);
	}

    @Test
	public void testCreateGeneralUserAllSpace() {
		//assertEquals(0, generalUserService.getAllGeneralUsers().size());
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
		assertEquals("The first name cannot be empty!", error);
	}

    @Test
	public void testCreateGeneralUserFistNameSpace() {
		//assertEquals(0, generalUserService.getAllGeneralUsers().size());
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

    @Test
	public void testCreateGeneralUserLastNameSpace() {
		//assertEquals(0, generalUserService.getAllGeneralUsers().size());
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

    @Test
	public void testCreateGeneralUserEmailSpace() {
		//assertEquals(0, generalUserService.getAllGeneralUsers().size());
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
	// To check with the rest of the team if we need to test a phone number of 0
//    @Test
//	public void testCreateGeneralUserPhoneNumberSpace() {
//		//assertEquals(0, generalUserService.getAllGeneralUsers().size());
//        String error = null;
//        String firstName = "Joe";
//        String lastName = "Doe";
//		String email = GENERALUSER_KEY;
//        int phoneNumber = 0;
//		GeneralUser generalUser = null;
//		try {
//			generalUser = generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);
//		} catch (IllegalArgumentException e) {
//			// Check that no error occurred
//			error = e.getMessage();
//		}
//		assertNull(generalUser);
//		assertEquals("GeneralUser phone number cannot be empty!", error);
//	}


    @Test
	public void testCreateGeneralUserEmailMissingAt() {
		//assertEquals(0, generalUserService.getAllGeneralUsers().size());
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

    @Test
	public void testCreateGeneralUserEmailMissingDot() {
		//assertEquals(0, generalUserService.getAllGeneralUsers().size());
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

    @Test
	public void testGetExistingGeneralUser() {
		assertEquals(GENERALUSER_KEY, generalUserService.getGeneralUser(GENERALUSER_KEY).getEmail());
	}

	@Test
	public void testGetNonExistingPerson() {
		assertNull(generalUserService.getGeneralUser("NotAGeneralUser"));
	}
}
