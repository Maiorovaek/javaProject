package tests;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.SpEL.BeanSpeL;

public class MainSpEL {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContent.xml");
        BeanSpeL beanSpeL = (BeanSpeL) context.getBean("beanSpEL");
        System.out.println("Name: " + beanSpeL.getName() + ", \n" + "Age: " + beanSpeL.getAge()  );

    }


}
