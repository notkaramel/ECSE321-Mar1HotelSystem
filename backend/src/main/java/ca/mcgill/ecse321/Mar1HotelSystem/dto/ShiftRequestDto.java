package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.Date;

public class ShiftRequestDto {

    // VARIABLES
    private Date date;
    private int startTime;
    private int endTime;

    // CONSTRUCTORS
    public ShiftRequestDto() {
    }

    // Shift constructor requiring employee, date, startTime, endTime
    public ShiftRequestDto(Date date, int startTime, int endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // GETTERS

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
}
