package tests;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.Circle;

public class AutowiredConstructor {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("autwiredConstructor.xml");
        Circle circle = (Circle) context.getBean("circleConstructor");
        circle.draw();
    }
}
