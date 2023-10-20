package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class ManagerDto extends AccountDto {
    
    // CONSTRUCTORS 
    public ManagerDto() {
        super();
    }

    // Manager constructor requiring firstName, lastName, email, phoneNUmber, password
    public ManagerDto(String firstName, String lastName, String email, int phoneNumber, String password) {
        super(firstName, lastName, email, phoneNumber, password);
    }

}
