import java.util.*;

public class Payment{
    // Defining variables
    private int amount;
    private String paymentId;
    

    public Payment(int amount, String paymentId){
        this.amount = amount;
        this.paymentId = paymentId;
    }

    //Getters
    public int getAmount(){
            return this.amount;
        }

    public String getPaymentId(){
            return this.paymentId;
        }


    //Setters
    public boolean setAmount(int amount){
        this.amount = amount;
        return true;
    }

    public boolean setPaymentId(String paymentId){
        this.paymentId = paymentId;
        return true;
    }

    public void delete(){
        
    }

}