package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Account;

public class AccountDto extends GeneralUserDto {

    // VARIABLES
    private String password;
    // Note: Not sure if we should send the password to the front end

    // CONSTRUCTORS
    public AccountDto() {
        super();
    }

    public AccountDto(String firstName, String lastName, String email, long phoneNumber, String password) {
        super(firstName, lastName, email, phoneNumber);
        this.password = password;
    }

    public AccountDto(Account account) {
        super(account);
        this.password = account.getPassword();
    }

    // GETTERS
    public boolean setPassword(String password) {
        this.password = password;
        return true;
    }

    public String getPassword() {
        return this.password;
    }
}