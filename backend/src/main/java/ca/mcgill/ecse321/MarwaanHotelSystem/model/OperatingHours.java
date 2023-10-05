package ca.mcgill.ecse321.MarwaanHotelSystem.model;



import ca.mcgill.ecse321.MarwaanHotelSystem.MarwaanHotelSystemApplication;

public class OperatingHours {
    // Defining variables
    private DayOfWeek day;
    private int openingHour;
    private int closingHour;
    private MarwaanHotelSystemApplication marwaanHotelSystemApplication;

  public OperatingHours(DayOfWeek day, int openingHour, int closingHour,
      MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
    this.day = day;
    this.openingHour = openingHour;
    this.closingHour = closingHour;
    if (setMarwaanHotelSystemApplication(marwaanHotelSystemApplication) == false) {
      throw new RuntimeException("Unable to create account due to marwaanHotelSystemApplication");
    }
  }

  // Enum
  public enum DayOfWeek {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
  }

  // Getters
  public DayOfWeek getDayOfWeek() {
    return this.day;
  }

  public int getOpeningHour() {
    return this.openingHour;
  }

  public int getClosingHour() {
    return this.closingHour;
  }

  // Setters
  public boolean setDayOfWeek(DayOfWeek day) {
    this.day = day;
    return true;
  }

  public boolean setOpeningHour(int openingHour) {
    this.openingHour = openingHour;
    return true;
  }

  public boolean setClosingHour(int closingHour) {
    this.closingHour = closingHour;
    return true;
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
            existingMarwaanHotelSystemApplication.removeOperatingHours(this);
            return false;
        }
        marwaanHotelSystemApplication.addOperatingHours(this);
        return true;
    }

    public void delete() {
        MarwaanHotelSystemApplication placeholderMarwaanHotelSystemApplication = marwaanHotelSystemApplication;
        this.marwaanHotelSystemApplication = null;
        if (placeholderMarwaanHotelSystemApplication != null) {
            placeholderMarwaanHotelSystemApplication.removeOperatingHours(this);
        }

    }


}