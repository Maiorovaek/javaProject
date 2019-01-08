package tests;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.Circle;

public class AutowiredType {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("autowiredType.xml");
        Circle circle = (Circle) context.getBean("circleNew");
        circle.draw();
    }
}
