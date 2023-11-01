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
}
