package test;
import consumer.MyApplication;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import services.MessageService;

@Configuration
@ComponentScan(value = "consumer")

public class MyApplicationTest {
    private AnnotationConfigApplicationContext context = null;

    @Bean
    public MessageService getMessageService() {
        return new MessageService() {
            public boolean sendMessage(String msg, String rec, int years) {
                System.out.println("Mock service");
                return true;
            }
        };
    }

    @Before
    public void setUp() throws Exception {
        context = new AnnotationConfigApplicationContext(MyApplicationTest.class);
    }

    @After
    public void tearDown() throws Exception {
        context.close();
    }

    @Test
    public void test() {
        MyApplication app = context.getBean(MyApplication.class);
        Assert.assertTrue(app.processMessage("Hi Dmitro!!!!", "dmytro@senor.com", 2012));
    }
}
