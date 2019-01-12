package com.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idshop", nullable = false)
    private int idshop;

    @Column(name = "name_shop", nullable = false)
    private String nameShop;


    @Column(name = "location_shop", nullable = false)
    private String locationShop;

    @Column(name = "commision", nullable = false)
    private double commision;

    public int getIdshop() {
        return idshop;
    }

    public String getNameShop() {
        return nameShop;
    }

    public String getLocationShop() {
        return locationShop;
    }

    public double getCommision() {
        return commision;
    }

    public void setIdshop(int idshop) {
        this.idshop = idshop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public void setLocationShop(String locationShop) {
        this.locationShop = locationShop;
    }

    public void setCommision(double commision) {
        this.commision = commision;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "idshop=" + idshop +
                ", nameShop='" + nameShop + '\'' +
                ", locationShop='" + locationShop + '\'' +
                ", commision='" + commision + '\'' +
                '}';
    }

    public Shop() {
    }

    public Shop(String nameShop, String locationShop, double commision) {
        this.nameShop = nameShop;
        this.locationShop = locationShop;
        this.commision = commision;
    }
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Purchase> purchases = new HashSet<>();

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

}
