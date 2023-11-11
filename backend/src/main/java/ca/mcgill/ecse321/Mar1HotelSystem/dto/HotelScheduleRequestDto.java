package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class HotelScheduleRequestDto {

    private int year;
    private int[] operatingHoursIdList;
    private int[] customHoursIdList;

    public HotelScheduleRequestDto() {
    }

    public HotelScheduleRequestDto(int year, int[] operatingHoursIdList, int[] customHoursIdList) {
        this.year = year;
        this.operatingHoursIdList = operatingHoursIdList;
        this.customHoursIdList = customHoursIdList;
    }

    public int getYear() {
        return this.year;
    }

    public int[] getOperatingHoursIdList() {
        return this.operatingHoursIdList;
    }

    public int[] getCustomHoursIdList() {
        return this.customHoursIdList;
    }
}
