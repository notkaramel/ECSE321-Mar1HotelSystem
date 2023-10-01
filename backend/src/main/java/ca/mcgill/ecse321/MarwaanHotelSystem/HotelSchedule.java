public class HotelSchedule{
    private int year;
    private List<CustomHours> customHoursList;
    private List<OperatingHours> operatingHoursList;

    public HotelSchedule(int year, OperatingHours...operatingHours, CustomHours... customHoursList){
        this.year = year;
        this.customHoursList = new ArrayList<CustomHours>();
        this.operatingHoursList = new ArrayList<OperatingHours>();
        if(customHours == null || customHours == false){
            throw new RuntimeException("Need an customHours class to be instatiated; need an custom hours");
        }

        if(operatingHours == null || operatingHours == false){
            throw new RuntimeException("Need an operatingHours class to be instatiated; need a operating hours");
        }

    }

    //Getters
    public String getYear(){
        return this.year;
    }

    public CustomHours getCustomHours(){
        return this.customHours;
    }

    public OperatingHours getOperatingHours(){
        return this.operatingHours;
    }

    //Setters
    public boolean setYear(int year){
        this.year = year;
        return true;
    }

// 7 association 
 public List<OperatingHours> getOperatingHours(){
        List<OperatingHours> newOperatingHours = Collections.unmodifiableList(this.operatingHours);
        return newOperatingHours;
        }

    public static int requiredNumberOfOperatingHours(){
        return 7
    }

    public static int minimumdNumberOfOperatingHours(){
        return 7
    }

    public static int maximumNumberOfOperatingHours(){
        return 7
    }

    public boolean setOperatingHours(OperatingHours...newOperatingHours){
        ArrayList<OperatingHours> theOperatingHoursList = new ArrayList<OperatingHours>();

        for (OperatingHours aOperatingHour : newOperatingHours){
            if (theOperatingHoursList.contains(aOperatingHour)){
                continue;
            }
            theOperatingHoursList.add(aOperatingHour);
            }
        if (theOperatingHoursList.size() != newOperatingHours.length || theOperatingHoursList.size() != requiredNumberOfOperatingHours()){
            return false;
            }
        this.operatingHours.clear();
        this.operatingHours.addAll(theOperatingHoursList);
        return true;
            }

    

// 365 association to CustomHour
    public List<CustomHours> getCustomHours(){
        List<CustomHours> newCustomHours = Collections.unmodifiableList(this.customHours);
        return newCustomHours;
        }

    public static int requiredNumberOfCustomHours(){
        return 365
    }

    public static int minimumdNumberOfCustomHours(){
        return 365
    }

    public static int maximumNumberOfCustomHours(){
        return 365
    }

    public boolean setCustomHours(CustomHours...newCustomHours){
        ArrayList<CustomHours> theCustomHoursList = new ArrayList<CustomHours>();

        for (CustomHours aCustomHour : newCustomHours){
            if (theCustomHoursList.contains(aCustomHour)){
                continue;
            }
            theCustomHoursList.add(aCustomHour);
            }
        if (theCustomHoursList.size() != newCustomHours.length || theCustomHoursList.size() != requiredNumberOfCustomHours()){
            return false;
            }
        this.customHours.clear();
        this.customHours.addAll(theCustomHoursList);
        return true;
            }


    public void delete(){
        this.operatingHours = null;
        this.customHours = null;
    }
    
}