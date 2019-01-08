package configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import services.EmailService;
import services.MessageService;

@Configuration
//для определения пакета в котором нужно искать классы компоненты
@ComponentScan(value = {"consumer"})
public class DIConfiguration {
//данный метод нужно использовать как бин для инъекций в классы компоненты
    @Bean
    public MessageService getMessageService(){
        return new EmailService();
    }
}
