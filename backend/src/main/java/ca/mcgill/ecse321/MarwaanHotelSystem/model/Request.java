package ca.mcgill.ecse321.MarwaanHotelSystem.model;

import ca.mcgill.ecse321.MarwaanHotelSystem.MarwaanHotelSystemApplication;
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
    private MarwaanHotelSystemApplication marwaanHotelSystemApplication;

    public Request(String description, Employee employee, Booking booking,
            MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
        this.description = description;
        if (setEmployee(employee) == false) {
            throw new RuntimeException("Need an employee class to be instatiated; need an employee");
        }

        if (setBooking(booking) == false) {
            throw new RuntimeException("Need an booking class to be instatiated; need a booking");
        }

        if (setMarwaanHotelSystemApplication(marwaanHotelSystemApplication) == false) {
            throw new RuntimeException("Unable to create account due to marwaanHotelSystemApplication");
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

    public MarwaanHotelSystemApplication getMarwaanHotelSystemApplication() {
        return marwaanHotelSystemApplication;
    }

    protected void clear_marwaanHotelSystemApplication() {
        marwaanHotelSystemApplication = null;
    }

    public boolean setMarwaanHotelSystemApplication(MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
        if (marwaanHotelSystemApplication == null) {
            return false;
        }

        MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = this.marwaanHotelSystemApplication;
        this.marwaanHotelSystemApplication = marwaanHotelSystemApplication;
        if (existingMarwaanHotelSystemApplication != null
                && !existingMarwaanHotelSystemApplication.equals(marwaanHotelSystemApplication)) {
            existingMarwaanHotelSystemApplication.removeRequest(this);
            return false;
        }
        marwaanHotelSystemApplication.addRequest(this);
        return true;
    }

    public void delete() {

        MarwaanHotelSystemApplication placeholderMarwaanHotelSystemApplication = marwaanHotelSystemApplication;
        this.marwaanHotelSystemApplication = null;
        if (placeholderMarwaanHotelSystemApplication != null) {
            placeholderMarwaanHotelSystemApplication.removeRequest(this);
        }

        this.employee = null;
        this.booking = null;
    }
}