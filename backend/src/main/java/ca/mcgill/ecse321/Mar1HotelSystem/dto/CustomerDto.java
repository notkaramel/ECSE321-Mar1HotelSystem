package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class CustomerDto extends AccountDto {
    
    // CONSTRUCTORS
    public CustomerDto() {
        super();
    }

    // Customer constructor requiring firstName, lastName, email, phoneNumber, password
    public CustomerDto(String firstName, String lastName, String email, long phoneNumber, String password) {
        super(firstName, lastName, email, phoneNumber, password);
    }

    // GETTERS
    public String getFirstName() {
        return super.getFirstName();
    }

    public String getLastName() {
        return super.getLastName();
    }

    public String getEmail() {
        return super.getEmail();
    }

    public long getPhoneNumber() {
        return super.getPhoneNumber();
    }

    public String getPassword() {
        return super.getPassword();
    }

}
