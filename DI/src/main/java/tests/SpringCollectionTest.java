package tests;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.BooksCollection;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SpringCollectionTest {
    public static void main(String[] args) {
        //applicationConfig.xml
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("applConfig.xml");
        BooksCollection booksCollection = (BooksCollection) context.getBean("booksNew");
booksCollection.displayWaiter();

    }
}
