package com.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int idCustomer;

    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "location", nullable = false)
    private String location;
    @Column(name = "discount", nullable = false)
    private double discount;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Purchase> purchase = new HashSet<>();


    public Set<Purchase> getPurchases() {
        return purchase;
    }

    public void setPurchases(Set<Purchase> purchase) {
        this.purchase = purchase;
    }


    public Customer() {
    }

    public Customer(String lastname, String location, double discount) {
        this.lastname = lastname;
        this.location = location;
        this.discount = discount;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public String getLastname() {
        return lastname;
    }

    public String getLocation() {
        return location;
    }

    public double getDiscount() {
        return discount;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer=" + idCustomer +
                ", lastname='" + lastname + '\'' +
                ", location='" + location + '\'' +
                ", discount=" + discount +
                '}';
    }
}
