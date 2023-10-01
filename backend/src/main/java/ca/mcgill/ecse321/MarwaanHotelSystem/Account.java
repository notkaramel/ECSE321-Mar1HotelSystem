import java.util.*;

public class Account extends User{

    // Defining Variables
    private String password;

    public Account(String firstName, String lastName, String email, int phoneNumber, String password){
        super(firstName, lastName, email, phoneNumber);
        this.password = password;
    }

    public boolean setPassword(String password){
        this.password = password;
        return true;
    }

    public String getPassword(){
        return this.password;
    }

    public void delete(){
        super.delete();
    }

}