package com.service;

import com.model.Books;

import java.util.List;
import java.util.Map;


public interface IBookService {
    void bookByIdDelete(int id);
    Books updateBookById(int id, String name);
    void addBook(Books book);
    List<Books> allBooks();
    long getBooksCount();
    Books findBookByID(int bookId);
    List<Books> getSpecialBooks();
    void getDataAboutBougthBook();
    Map<String, Double> getAllTitleAndCost();
}
