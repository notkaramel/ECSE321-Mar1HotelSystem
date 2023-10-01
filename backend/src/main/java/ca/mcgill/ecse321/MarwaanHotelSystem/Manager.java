import java.util.*;

public class Manager extends Account{

    public Manager(String firstName, String lastName, String email, int phoneNumber, String password){
        super(firstName, lastName, email, phoneNumber, password);
    }

     public void delete(){
        super.delete();
    }
}