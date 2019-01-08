package services;

public class TwitterService implements MessageService {
    public boolean sendMessage(String msg, String rec,int years) {
        System.out.println("Twitter message Sent to "+ rec + " with message " + msg + " years: " + years);
        return true;
    }
}
