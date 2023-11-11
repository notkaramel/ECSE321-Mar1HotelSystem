package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class HotelScheduleRequestDto {

    private int year;
    private int[] customHoursIdList;
    private int[] operatingHoursIdList;

    public HotelScheduleRequestDto() {
    }

    public HotelScheduleRequestDto(int year, int[] operatingHoursIdList, int[] customHoursIdList) {
        this.year = year;
        this.customHoursIdList = customHoursIdList;
        this.operatingHoursIdList = operatingHoursIdList;
    }

    public int getYear() {
        return this.year;
    }
    public int[] getCustomHoursIdList() {
        return this.customHoursIdList;
    }
    public int[] getOperatingHoursIdList() {
        return this.operatingHoursIdList;
    }
}
