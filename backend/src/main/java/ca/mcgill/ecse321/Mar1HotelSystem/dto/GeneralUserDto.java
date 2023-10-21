package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class GeneralUserDto{
    
    // VARIABLES
    private String email;
    private String firstName;
    private String lastName;
    private int phoneNumber;

    // CONSTRUCTORS
    public GeneralUserDto() {
    }

    // Shift constructor requiring firstName, lastName, email, phoneNumber
    public GeneralUserDto(String firstName, String lastName, String email, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // GETTERS 
    // Method to get firstName, returns firstName
    public String getFirstName() {
        return this.firstName;
    }

    // Method to get lastName, returns lastName
    public String getLastName() {
        return this.lastName;
    }

    // Method to get email, returns email
    public String getEmail() {
        return this.email;
    }

    // Method to get phoneNumber, returns phoneNumber
    public int getPhoneNumber() {
        return this.phoneNumber;
    }

}