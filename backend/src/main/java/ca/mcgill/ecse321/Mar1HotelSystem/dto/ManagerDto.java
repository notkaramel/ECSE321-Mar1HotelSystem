package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;

public class ManagerDto extends AccountDto {

    // CONSTRUCTORS
    public ManagerDto() {
        super();
    }

    // Manager constructor requiring firstName, lastName, email, phoneNUmber,
    // password
    public ManagerDto(String firstName, String lastName, String email, long phoneNumber, String password) {
        super(firstName, lastName, email, phoneNumber, password);
    }

    // Shift constructor requiring Manager
    public ManagerDto(Manager manager) {
        super(manager);
    }

}
