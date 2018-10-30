package books;

import java.util.Arrays;

public class Book extends Author {
    private String name;
    private Author[] authors;
    private double price;
    private int qty = 0;

    public Book(String name, Author[] authors, double price) {
        this.name = name;
        this.authors = authors;
        this.price = price;
    }

    public Book(String name, Author[] authors, double price, int qty) {
        this.name = name;
        this.authors = authors;
        this.price = price;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", authors=" + getAuthorNames() +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }

    public String getAuthorNames() {
        String names = " ";
        for (int i = 0; i < authors.length; i++) {
            String currentName = authors[i].getName();
            names = names + currentName + " ";
        }
        return names;

    }
}
