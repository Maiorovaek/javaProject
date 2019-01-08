package tests;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.lookup.IStudent;
import services.lookup.Student;

public class LookupTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("lookup.xml");
        IStudent studentAbout = (IStudent) context.getBean("studentAbout");
        IStudent studentInfo = (IStudent) context.getBean("studentInfo");


        displayInfo(studentAbout);
        displayInfo(studentInfo);
    }
    public static void displayInfo(IStudent student) {
        student.about();
    }
}
