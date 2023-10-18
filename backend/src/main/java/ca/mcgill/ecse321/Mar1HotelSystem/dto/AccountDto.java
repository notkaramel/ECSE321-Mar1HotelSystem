package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class AccountDto {
    private String password;

    public AccountDto() {
    }

    /* 
    public EventDto(String name) {
		this(name, Date.valueOf("1971-01-01"), Time.valueOf("00:00:00"), Time.valueOf("23:59:59"));
	}
    */ 

    public AccountDto(String password) {
        this.password = password
    }

    public String getPassword() {
        return password
    }


}