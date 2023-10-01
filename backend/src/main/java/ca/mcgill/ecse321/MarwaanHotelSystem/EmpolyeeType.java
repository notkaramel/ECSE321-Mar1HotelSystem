public EmployeeType{
    
    private String kitchenStaff;
    private String cleaningStaff;
    private String reception;

    public EmployeeType(String kitchenStaff, String cleaningStaff, String reception){
        this.kitchenStaff = kitchenStaff;
        this.cleaningStaff = cleaningStaff;
        this.reception = reception;
    }

    //Getters
    public String getKitchenStaff(){
            return this.kitchenStaff;
        }

    public String getCleaningStaff(){
            return this.cleaningStaff;
        }

    public String getReception(){
            return this.reception;
        }

    //Setters
    public boolean setKitchenStaff(String kitchenStaff){
        this.kitchenStaff = kitchenStaff;
        return true;
    }

    public boolean setCleaningStaff(String cleaningStaff){
        this.cleaningStaff = cleaningStaff;
        return true;
    }

    public boolean setReception(String reception){
        this.reception = reception;
        return true;
    }

    public void delete(){
        
    }

}