package tests;

import consumer.MyXMLApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientXMLApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContent.xml");
        MyXMLApplication app = context.getBean(MyXMLApplication.class);
        app.processMessage("Hy Dmitro", "dmitro@senior.com", 2005);
       context.close();
    }
}
