package com;

import com.config.SpringConfig;
import com.model.Books;
import com.service.CustomerService;
import com.service.BookService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
       BookService bookService = (BookService) context.getBean("bookService");
        System.out.println(bookService.allBooks());

        Books book1 = new Books("Wolf", 2400.00, "Point2", 2);
        bookService.addBook(book1);
        System.out.println(bookService.allBooks());

        System.out.println(bookService.getBooksCount());

        CustomerService customerService = (CustomerService) context.getBean("customerService");
        System.out.println(customerService.allCustomers());
        System.out.println(customerService.findCustomerByID(5));
    }


}
