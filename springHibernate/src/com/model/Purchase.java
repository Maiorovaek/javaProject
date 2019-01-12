package com.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchase")
public class Purchase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number_oder", nullable = false)
    private int numberOder;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "seller", nullable = false)
    private int shopId;

    @Column(name = "customer", nullable = false)
    private int custId;

    @Column(name = "book", nullable = false)
    private int bookId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "sum", nullable = false)
    private int sum;


    @ManyToOne
    @JoinColumn(name = "seller", insertable = false, updatable = false)
    private Shop shop;


    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @ManyToOne
    @JoinColumn(name = "book", insertable = false, updatable = false)
    private Books books;

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    @ManyToOne
    @JoinColumn(name = "customer", insertable = false, updatable = false)
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



    public int getNumberOder() {
        return numberOder;
    }

    public Date getDate() {
        return date;
    }

    public int getShopId() {
        return shopId;
    }

    public int getCustId() {
        return custId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSum() {
        return sum;
    }

    public void setNumberOder(int numberOder) {
        this.numberOder = numberOder;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Purchase() {
    }

    public Purchase(Date date, int shopId, int custId, int bookId, int quantity, int sum) {
        this.date = date;
        this.shopId = shopId;
        this.custId = custId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "numberOder=" + numberOder +
                ", date=" + date +
                ", shopId=" + shopId +
                ", custId=" + custId +
                ", bookId=" + bookId +
                ", quantity=" + quantity +
                ", sum=" + sum +
                '}';
    }
}