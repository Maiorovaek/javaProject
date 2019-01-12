package com.dao;

import com.model.Books;

import java.util.List;

public interface BookDAO {
    void bookByIdDelete(int id);

    Books updateBookById(int id, String title);

    void addBook(Books book);

    List<Books> allBooks();

    long getBooksCount();

    Books findBookByID(int bookId);

    List<Books> getSpecialBooks();

    List getDataAboutBougthBook();


}
