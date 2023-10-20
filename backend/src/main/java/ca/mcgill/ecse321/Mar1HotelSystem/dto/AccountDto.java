package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class AccountDto extends GeneralUserDto {
    
    // VARIABLES
    private String password;

    // CONSTRUCTORS
    public AccountDto(){ 
        super();
    }

    public AccountDto(String firstName, String lastName, String email, int phoneNumber, String password) {
        super(firstName, lastName, email, phoneNumber);
        this.password = password;
    }

    // GETTERS
    public boolean setPassword(String password) {
        this.password = password;
        return true;
    }

}