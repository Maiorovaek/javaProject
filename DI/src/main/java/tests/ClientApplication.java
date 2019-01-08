package tests;

import configuration.DIConfiguration;
import consumer.MyApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientApplication {
    public static void main(String[] args) {
//AnnotationConfigApplicationContext для автоматического связывания
// сервисов с компонентами когда используют аннотации для конфигурации фреймворка Spring.
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
        //getBean возвращает объект компонента и использует конфигурацию для автоматического связывания(autowiring) объектов.
        MyApplication app = context.getBean(MyApplication.class);
        app.processMessage("Hi Dmitro", "dmytro@senior.com", 2002);
        context.close();
    }
}
