package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class AccountDto {
    
    // Defining Variables
    private String password;

    public Account() { 
        //super()
    }

    // Account constructor requiring firstName, lastName, email, phoneNumber,
    // password
    public Account(String firstName, String lastName, String email, int phoneNumber, String password) {
        //super(firstName, lastName, email, phoneNumber);
        this.password = password;
    }

    // Method to set password, returns true if password successfully set
    public boolean setPassword(String password) {
        this.password = password;
        return true;
    }

    // Method to get password, returns password
    public String getPassword() {
        return this.password;
    }

}