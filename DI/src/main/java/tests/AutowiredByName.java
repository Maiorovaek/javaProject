package tests;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.Shape;

public class AutowiredByName {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("awtowiring.xml");
        Shape shape = (Shape)context.getBean("circle");
        shape.draw();
    }


}
