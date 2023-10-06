package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.AccountRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Account;

@SpringBootTest
public class AccountRepositoryTest {
    @Autowired
	private AccountRepository accountRepository;

	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		accountRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadAccount() {
		// Create account
		String firstName = "John";
		String lastName = "Doe";
		String email = "johndoe@gmail.com";
        int phoneNumber = 111333333;
        String password = "abc";
		Account account = new Account(firstName, lastName, email, phoneNumber, password);
		account.setFistName(firstName);
        account.setLastName(lastName);
        account.setEmail(email);
        account.setPhoneNumber(phoneNumber);
        account.setPassword(password);

		// Save account
		accountRepository.save(account);

		// Read account from database.
		account = accountRepository.findAccountByEmail(email);

		// Assert that account is not null and has correct attributes.
		assertNotNull(account);
        assertEquals(firstName, account.getFirstName());
        assertEquals(lastName, account.getLastName());
		assertEquals(email, account.getEmail());
		assertEquals(phoneNumber, account.getPhoneNumber());
		assertEquals(password, account.getPassword());
	}
}
