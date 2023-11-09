// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.Entity;

/**
 * The Manager class (special for Mr. Marwan) in the system.
 * 
 * @author Lucas Pacicco (@Lucaspac5) - Boilerplate Code
 * @author Bilar Mokhtari (@bmokhtari) - JPA Annotations
 * @author Antoine Phan (@notkaramel) - JPA Annotations
 */
@Entity
public class Manager extends Account {

    public Manager() {
        super();
    }

    // Manager constructor requiring firstName, lastName, email, phoneNUmber,
    // password
    public Manager(String firstName, String lastName, String email, long phoneNumber, String password) {
        super(firstName, lastName, email, phoneNumber, password);
    }

    // Method to delete
    public void delete() {
        super.delete();
    }
}