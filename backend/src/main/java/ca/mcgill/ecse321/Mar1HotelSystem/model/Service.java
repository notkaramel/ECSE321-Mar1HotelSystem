package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.*;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serviceId;
    @OneToOne
    private Employee assignee;
    @OneToOne
    private Request request;

    public Service(Employee assignee, Request request) {
        if (setEmployee(assignee) == false) {
            throw new RuntimeException("Need an employee class to be instatiated; need an employee");
        }

        if (setBooking(request) == false) {
            throw new RuntimeException("Need an booking class to be instatiated; need a booking");
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

    public void delete() {
        this.assignee = null;
        this.request = null;
    }
}
