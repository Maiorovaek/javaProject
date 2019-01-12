package com.service;
import com.dao.BookDAO;
import com.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("bookService")
public class BookServiceImpl implements BookService {

  @Autowired
  BookDAO bookDAO;

    public void bookByIdDelete(int id) {
        bookDAO.bookByIdDelete(id);
    }



    @Override
    public Books updateBookById(int id, String name) {
        return bookDAO.updateBookById(id, name);
    }

    @Override
    public void addBook(Books book) {
        bookDAO.addBook(book);
    }

    @Override
    public List<Books> allBooks() {
        return bookDAO.allBooks();
    }

    @Override
    public long getBooksCount() {
        return bookDAO.getBooksCount();
    }

    @Override
    public Books findBookByID(int bookId) {
        return bookDAO.findBookByID(bookId);
    }

    @Override
    public List<Books> getSpecialBooks() {
return bookDAO.getSpecialBooks();
    }

    @Override
    public void getDataAboutBougthBook() {
        List list = bookDAO.getDataAboutBougthBook();
        for(Object obj : list) {
            Object[] arr = (Object[]) obj;
            System.out.println("Name = " + arr[0] + ", price = " + arr[1] + ", location = " + arr[2] + ", quantity = " + arr[3]  + "\n" );
        }
    }




    public Map<String, Double> getAllTitleAndCost() {
        Map<String, Double> desiredList = new HashMap<String, Double>();
        List<Books> allBooks = bookDAO.allBooks();
        for(Books book : allBooks) {
            String name_book = book.getName_book();
            Double price = book.getPrice();
            desiredList.put(name_book, price);
        }
        return desiredList;
    }
}
