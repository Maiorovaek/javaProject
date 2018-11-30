package book.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Book {
    private String name;
    private String author;
    private int price;
    private int count;
    private int data;

    public Book() {
    }

    public Book(String name, String author, int price, int count, int data) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.count = count;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        if(price < 0) {
            throw new IllegalArgumentException("Price can't be <0");
        }
        return price;
    }
    public int setData() {
      if (data<2019){
          throw new IllegalArgumentException("Data can't be >2018");
      }
        return data;
    }


    public void setData(int data) {
        this.data = data;
    }



    public int getData() { return data; }

    @Override
    public String toString() {
        return   name + ", " + author + ", " + price + ", " + count + ", " + data;
    }
}