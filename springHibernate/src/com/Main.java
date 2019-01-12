package com;

import com.config.SpringConfig;
import com.model.Books;
import com.service.CustomerService;
import com.service.IBookService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
       IBookService bookService = (IBookService) context.getBean("bookService");
//        System.out.println(bookService.allBooks());
//
//        Books book1 = new Books("Taddy", 2400.00, "Point1", 2);
//        bookService.addBook(book1);
//        System.out.println(bookService.allBooks());
//
//        System.out.println(bookService.getBooksCount());

        CustomerService customerService = (CustomerService) context.getBean("customerService");
        System.out.println(customerService.allCustomers());
        System.out.println(customerService.findCustomerByID(5));
    }


}
