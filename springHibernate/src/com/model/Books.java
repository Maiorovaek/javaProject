package com.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Books {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book", nullable = false)
    private int idBook;

    @Column(name = "name_book", nullable = false)
    private String name_book;

    @Column(name = "price", nullable = false)
    private double price;


    @Column(name = "stock", nullable = false)
    private String stock;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @OneToMany(mappedBy = "books", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Purchase> purchase = new HashSet<>();


    public Set<Purchase> getPurchases() {
        return purchase;
    }

    public void setPurchases(Set<Purchase> purchase) {
        this.purchase = purchase;
    }





    public Books(String name_book, Double price, String stock, Integer quantity) {

        this.name_book = name_book;
        this.price = price;
        this.stock = stock;
        this.quantity = quantity;
    }

    public Books() {
    }

    public int getIdBook() {
        return idBook;
    }

    public String getName_book() {
        return name_book;
    }

    public double getPrice() {
        return price;
    }

    public String getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }


    public void setName_book(String name_book) {
        this.name_book = name_book;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Books{" +
                "idBook=" + idBook +
                ", name_book='" + name_book + '\'' +
                ", price=" + price +
                ", stock='" + stock + '\'' +
                ", quantity=" + quantity +
                '}';
    }

}
