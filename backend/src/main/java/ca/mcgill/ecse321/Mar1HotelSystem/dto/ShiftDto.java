package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.Date;

public class ShiftDto {

    // VARIABLES
    private int shiftId;
    private Date date;
    private int startTime;
    private int endTime;
    private EmployeeDto employee;

    // CONSTRUCTORS
    public ShiftDto() {
    }

    // Shift constructor requiring employee, date, startTime, endTime
    public ShiftDto(EmployeeDto employee, Date date, int startTime, int endTime) {
        this.employee = employee;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // GETTERS
    // Method to get employee, returns employee
    public EmployeeDto getEmployee() {
        return this.employee;
    }

    // Method to get date, returns date
    public Date getDate() {
        return this.date;
    }

    // Method to get startTime, returns startTime
    public int getStartTime() {
        return this.startTime;
    }

    // Method to get endTime, returns endTime
    public int getEndTime() {
        return this.endTime;
    }

    public int getShiftId() {
        return this.shiftId;
    }

}
