package services;



public class EmailService implements MessageService {


    public boolean sendMessage(String msg, String rec, int years) {
        System.out.println("Email Sent to " + rec + " with message = " + msg +  " years: " + years);
        return true;
    }


}
