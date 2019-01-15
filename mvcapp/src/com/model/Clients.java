package com.model;

import javax.persistence.*;


@Entity
@Table(name = "clients")
public class Clients {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_client", nullable = false)
    private int idClient;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "area", nullable =  false)
    private  String area;

    @Column( name = "discount", nullable = false)
    private int discount;

    public Clients(String lastname, String area, int discount) {
        this.lastname = lastname;
        this.area = area;
        this.discount = discount;
    }

    public Clients() {
    }

    @Override
    public String toString() {
        return "Clients{" +
                "idClient=" + idClient +
                ", lastname='" + lastname + '\'' +
                ", area='" + area + '\'' +
                ", discount=" + discount +
                '}';
    }

    public int getIdClient() {
        return idClient;
    }

    public String getLastname() {
        return lastname;
    }

    public String getArea() {
        return area;
    }

    public int getDiscount() {
        return discount;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
