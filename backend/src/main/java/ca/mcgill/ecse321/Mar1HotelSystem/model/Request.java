package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int requestId;
    private String description;
    private Employee employee;
    @OneToOne
    private Booking booking;

    public Request(String description, Employee employee, Booking booking) {
        this.description = description;
        if (setEmployee(employee) == false) {
            throw new RuntimeException("Need an employee class to be instatiated; need an employee");
        }

        if (setBooking(booking) == false) {
            throw new RuntimeException("Need an booking class to be instatiated; need a booking");
        }
    }

    // Getters
    public String getDescription() {
        return this.description;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public Booking getBooking() {
        return this.booking;
    }

    // Setters
    public boolean setDescription(String description) {
        this.description = description;
        return true;
    }

    public boolean setEmployee(Employee employee) {
        if (employee != null) {
            this.employee = employee;
            return true;
        } else {
            return false;
        }
    }

    public boolean setBooking(Booking booking) {
        if (booking != null) {
            this.booking = booking;
            return true;
        } else {
            return false;
        }
    }

    public void delete() {
        this.employee = null;
        this.booking = null;
    }
}