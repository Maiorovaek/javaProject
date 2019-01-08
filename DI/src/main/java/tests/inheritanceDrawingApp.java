package tests;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.Triangle;

public class inheritanceDrawingApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContent.xml");
        Triangle triangle = (Triangle) context.getBean("triangle1");
        triangle.draw();

    }
}
