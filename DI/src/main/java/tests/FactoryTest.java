package tests;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.factory.ATM;


public class FactoryTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("factoryContext.xml");
        ATM atm = ctx.getBean("atm", ATM.class);
        atm.bookInformation("Thinking in Java");
        ctx.getBean("bookATM");
    }
}
