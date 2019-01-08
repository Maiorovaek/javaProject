package consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.MessageService;

@Component
public class MyApplication {

    //@Autowired
    private MessageService service;


    //constructor-based dependency injection
//    @Autowired
//    public MyApplication(MessageService svc) {
//        this.service = svc;
//    }
//setter-based dependency or method-based dependency injection
    @Autowired
    public void setService(MessageService svc) {
        this.service = svc;
    }

    public boolean processMessage(String msg, String rec, int years) {
        return this.service.sendMessage(msg, rec,years);
    }


}
