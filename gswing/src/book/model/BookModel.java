package book.model;

import book.entity.Book;

import javax.swing.table.AbstractTableModel;
import java.util.*;

public class BookModel extends AbstractTableModel {

    private List<Book> books = new ArrayList<>();

    public BookModel() {
        readFile();

        books.add(new Book("jAVA","AUTHOR",100, 5,2011));
    }

    private void readFile() {
        fireTableDataChanged();
    }

    public void addBook(Book b) {
         books.add(b);
         fireTableDataChanged();
    }

    public void addNewBook(Book b) {
        fireTableDataChanged();
    }


    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book cur = books.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return cur.getName();
            case 1:
                return cur.getAuthor();
            case 2:
                return cur.getPrice();
            case 3:
                return cur.getCount();
            case 4:
                return cur.getData();
        }
        return null;
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Book name";
            case 1:
                return "Book Author";
            case 2:
                return "Price";
            case 3:
                return "Count";
            case 4:
                return "Data";
        }
        return "";
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            case 3:
                return Integer.class;
            case 4:
                return Integer.class;
        }
        return Object.class;
    }
    public void deleteBook(int index) {

        books.remove(index);

        fireTableDataChanged();

    }
    public void clean() {

        books.clear();

    }


    public Book returnBook() {
        for (Book book : books) {
            return book;
        }
        return null;
    }
    public void editBook(Book b, int index){

        books.set(index, b);
        fireTableDataChanged();

    }

    public int size() {
        return books.size();
    }

    public Book get(int index) {

        return books.get(index);
    }

    public List<Book> getBooks() {
        return this.books;
    }

}







