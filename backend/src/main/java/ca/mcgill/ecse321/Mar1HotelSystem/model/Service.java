package ca.mcgill.ecse321.Mar1HotelSystem.model;

import ca.mcgill.ecse321.Mar1HotelSystem.Mar1HotelSystemApplication;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OneToOne
    private Employee assignee;
    @OneToOne
    private Request request;
    private Mar1HotelSystemApplication mar1HotelSystemApplication;

    public Service(Employee assignee, Request request,
            Mar1HotelSystemApplication mar1HotelSystemApplication) {
        if (setEmployee(assignee) == false) {
            throw new RuntimeException("Need an employee class to be instatiated; need an employee");
        }

        if (setBooking(request) == false) {
            throw new RuntimeException("Need an booking class to be instatiated; need a booking");
        }

        if (setMar1HotelSystemApplication(mar1HotelSystemApplication) == false) {
            throw new RuntimeException("Unable to create account due to mar1HotelSystemApplication");
        }

    }

    // Getters
   
    public Employee getAssignee() {
        return this.assignee;
    }

    public Request getRequest() {
        return this.request;
    }

    // Setters

    public boolean setEmployee(Employee assignee) {
        if (assignee != null) {
            this.assignee = assignee;
            return true;
        } else {
            return false;
        }
    }

    public boolean setBooking(Request request) {
        if (request != null) {
            this.request = request;
            return true;
        } else {
            return false;
        }
    }

    public Mar1HotelSystemApplication getMar1HotelSystemApplication() {
        return mar1HotelSystemApplication;
    }

    protected void clear_mar1HotelSystemApplication() {
        mar1HotelSystemApplication = null;
    }

    public boolean setMar1HotelSystemApplication(Mar1HotelSystemApplication mar1HotelSystemApplication) {
        if (mar1HotelSystemApplication == null) {
            return false;
        }

        Mar1HotelSystemApplication existingMar1HotelSystemApplication = this.mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = mar1HotelSystemApplication;
        if (existingMar1HotelSystemApplication != null
                && !existingMar1HotelSystemApplication.equals(mar1HotelSystemApplication)) {
            existingMar1HotelSystemApplication.removeService(this);
            return false;
        }
        mar1HotelSystemApplication.addService(this);
        return true;
    }

    public void delete() {

        Mar1HotelSystemApplication placeholderMar1HotelSystemApplication = mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = null;
        if (placeholderMar1HotelSystemApplication != null) {
            placeholderMar1HotelSystemApplication.removeService(this);
        }

        this.assignee = null;
        this.request = null;
    }
}
