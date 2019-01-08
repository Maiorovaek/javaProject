package consumer;

import services.MessageService;

public class MyXMLApplication {
    private MessageService service;

    public MyXMLApplication(MessageService svc){
        this.service = svc;
    }

//    public void setService(MessageService svc){
//        this.service=svc;
//    }
    public boolean processMessage(String msg, String rec, int years){
        return this.service.sendMessage(msg,rec, years);
    }

}
