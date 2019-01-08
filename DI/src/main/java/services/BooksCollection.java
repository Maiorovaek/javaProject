package services;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class BooksCollection {
    private List<String> bookList;
    private Set<String> bookSet;
    private Map<Integer,String> bookMap;
    public BooksCollection(){}

    public BooksCollection(List<String> bookList, Set<String> bookSet, Map<Integer,String> bookMap) {
        this.bookList=bookList;
        this.bookMap =bookMap;
        this.bookSet = bookSet;
    }

    public void setBookList(List<String> bookList) {
        this.bookList=bookList;
    }

    public void setBookSet(Set<String> bookSet) {
        this.bookSet = bookSet;
    }

    public void setBookMap(Map<Integer,String> bookMap) {
    this.bookMap = bookMap;
    }

    public List<String> getBookList() {
        return bookList;
    }

    public Set<String> getBookSet() {
        return bookSet;
    }

    public Map<Integer, String> getBookMap() {
        return bookMap;
    }

    public void displayWaiter() {
        System.out.println("All books: " +  bookList);
        System.out.println("All books set: " + bookSet);
       // System.out.println("All books map: " + bookMap);
        for(Map.Entry book: bookMap.entrySet())
            System.out.println("All books: "+book.getKey()+"("+book.getValue()+")");


    }
}
