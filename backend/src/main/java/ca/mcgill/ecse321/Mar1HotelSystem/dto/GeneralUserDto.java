package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;

public class GeneralUserDto{
    
    // VARIABLES
    private String email;
    private String firstName;
    private String lastName;
    private long phoneNumber;

    // CONSTRUCTORS
    public GeneralUserDto() {
    }

    // Shift constructor requiring firstName, lastName, email, phoneNumber
    public GeneralUserDto(String firstName, String lastName, String email, long phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

     // Shift constructor requiring firstName, lastName, email, phoneNumber
    public GeneralUserDto(GeneralUser generalUser) {
        this.firstName = generalUser.getFirstName();
        this.lastName = generalUser.getLastName();
        this.email = generalUser.getEmail();
        this.phoneNumber = generalUser.getPhoneNumber();
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
    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    // // Setters
	// 	// Method to set firstName, returns true if firstName set
	// 	public boolean setFistName(String firstName) {
	// 		this.firstName = firstName;
	// 		return true;
	// 	}

	// 	// Method to set lastName, returns true if lastName set
	// 	public boolean setLastName(String lastName) {
	// 		this.lastName = lastName;
	// 		return true;
	// 	}

	// 	// Method to set email, returns true if email set
	// 	public boolean setEmail(String email) {
	// 		this.email = email;
	// 		return true;
	// 	}

	// 	// Method to set phoneNumber, returns true if phoneNumber set
	// 	public boolean setPhoneNumber(int phoneNumber) {
	// 		this.phoneNumber = phoneNumber;
	// 		return true;
	// 	}

}