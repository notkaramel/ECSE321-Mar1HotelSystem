public DayOfWeek{
    
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;

    public DayOfWeek(String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday){
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
         this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.saturday = sunday;
    }

    //Getters
    public String getMonday(){
            return this.monday;
        }

    public String getTuesday(){
            return this.tuesday;
        }

    public String getWednesday(){
            return this.wednesday;
        }

        public String getThursday(){
            return this.thursday;
        }

    public String getFriday(){
            return this.friday;
        }

    public String getSaturday(){
            return this.saturday;
        }

    public String getSunday(){
        return this.sunday;
    }

    //Setters
    public boolean setMonday(String monday){
        this.monday = monday;
        return true;
    }

    public boolean setTuesday(String tuesday){
        this.tuesday = tuesday;
        return true;
    }

    public boolean setWednesday(String wednesday){
        this.wednesday = wednesday;
        return true;
    }

    public boolean setThursday(String thursday){
        this.thursday = thursday;
        return true;
    }

    public boolean setFriday(String friday){
        this.friday = friday;
        return true;
    }

    public boolean setSaturday(String saturday){
        this.saturday = saturday;
        return true;
    }

    public boolean setSunday(String sunday){
        this.sunday = sunday;
        return true;
    }

    public void delete(){
        
    }

}