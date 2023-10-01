package ca.mcgill.ecse321.MarwaanHotelSystem.model;

public class Customer extends Account {

    public Customer(String firstName, String lastName, String email, int phoneNumber, String password) {
        super(firstName, lastName, email, phoneNumber, password);
    }

    public void delete() {
        super.delete();
    }

}