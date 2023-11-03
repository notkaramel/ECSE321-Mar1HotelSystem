package ca.mcgill.ecse321.Mar1HotelSystem.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class GeneralUserServiceTest {

    @Mock
    private GeneralUserRepository generalUserDao;

    @InjectMocks
    private GeneralUserService generalUserService;

    private static final String GENERALUSER_KEY = "joe@gmail.com";

    @BeforeEach
    public void setMockOutput() {
        lenient().when(generalUserDao.findGeneralUserByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(GENERALUSER_KEY)) {
                GeneralUser generalUser = new GeneralUser();
                generalUser.setEmail(GENERALUSER_KEY);
                return generalUser;
            } else {
                return null;
            }
        });
		lenient().when(generalUserDao.findAll()).thenAnswer((invocation) -> {
            List<GeneralUser> generalUsers = new ArrayList<>();
            GeneralUser generalUser1= new GeneralUser();
			GeneralUser generalUser2= new GeneralUser();
            generalUsers.add(generalUser1);
			generalUsers.add(generalUser2);
            return generalUsers;
        }); //save, update, delete

		// lenient().when(generalUserDao.save()).thenAnswer((invocation) -> {
        //      	GeneralUser generalUser = new GeneralUser();
        //         generalUser.setEmail(GENERALUSER_KEY);
        //         return generalUser;
            
        // });
}

    @Test
	public void testCreateGeneralUser() {
		//assertEquals(0, generalUserService.getAllGeneralUsers().size());
        
        String firstName = "Joey";
        String lastName = "Doey";
		String email = "joey@gmail.com";
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

	@Test
	public void testCreateGeneralUserTwice() {
		//assertEquals(0, generalUserService.getAllGeneralUsers().size());
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
		
		assertEquals("User with that email already exists!", error);
		
	}

	@Test
    public void testCreateGeneralUserNull() {
		//assertEquals(0, generalUserService.getAllGeneralUsers().size());
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
		assertEquals("All fields are empty!", error);
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
	public void testGetGeneralUserSuccessful() {
		String error = null;
		
		try {
			GeneralUser generalUser = generalUserService.getGeneralUser("joe@gmail.com");
			assertNotNull(generalUser);
			assertEquals("joe@gmail.com", generalUser.getEmail());
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		
	}

	@Test
	public void testGetGeneralUserUnSuccessful() {
		//assertEquals(0, generalUserService.getAllGeneralUsers().size());
        String error = null;
		try {
			generalUserService.getGeneralUser("jane@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertEquals("User Not Found", error);
	}

	@Test
	public void testGetGeneralUserListSuccessful() {
		String error = null;
		String firstName2 = "Joee";
        String lastName2 = "Doee";
		String email2 = "joee@gmail.com";
        int phoneNumber2 = 1234567891;
		generalUserService.createGeneralUser(firstName2, lastName2, email2, phoneNumber2);
		try {
			List<GeneralUser> generalUsers = generalUserService.getAllGeneralUsers();
			assertNotNull(generalUsers);
		 	assertEquals("joe@gmail.com", generalUsers.get(0).getEmail());
		 	assertEquals("joee@gmail.com", generalUsers.get(1).getEmail());
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
	    
		
	}

	@Test
	public void testGetGeneralUserListUnsuccessfulNull() {
		String error = null;
		try {
		List<GeneralUser> generalUsers = generalUserService.getAllGeneralUsers();
		
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		 assertEquals("There are no Users found!", error);
		 
	}
	

	@Test
	public void testUpdateGeneralUserThatExist() {
		String error = null;
        String firstName = "Joe";
        String lastName = "Doe";
		String email = "joe@gmail.com";
        int phoneNumber = 1234567891;
		GeneralUser generalUser = null;
		generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);
		try {
			generalUser = generalUserService.updateGeneralUserEmail("joe@gmail.com", "jane@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNotNull(generalUser);
		assertEquals("jane@gmail.com", generalUser.getEmail());
	}

	@Test
	public void testUpdateGeneralUserThatDoesNotExist() {
		String error = null;
        String firstName = "Joe";
        String lastName = "Doe";
		String email = "joe@gmail.com";
        int phoneNumber = 1234567891;
		GeneralUser generalUser = null;
		generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);
		try {
			generalUser = generalUserService.updateGeneralUserEmail("Gio@gmail.com", "jane@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(generalUser);
		assertEquals("User Not Found", error);
	}

	@Test
	public void testDeleteGeneralUserThatExist() {
		 String error = null;
        String firstName = "Joe";
        String lastName = "Doe";
		String email = "joee@gmail.com";
        int phoneNumber = 1234567891;
		Boolean generalUser = null;
		generalUserService.createGeneralUser(firstName, lastName, email, phoneNumber);
		try {
			generalUser = generalUserService.deleteGeneralUser("joee@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertEquals(true, generalUser);
	}

    @Test
	public void testGetExistingGeneralUser() {
		assertEquals(GENERALUSER_KEY, generalUserService.getGeneralUser(GENERALUSER_KEY).getEmail());
	}

}
