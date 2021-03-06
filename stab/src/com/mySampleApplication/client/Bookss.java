package com.mySampleApplication.client;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;


public class Bookss implements Serializable {

public Bookss(){

}
    private  Integer id;
    private  String author;
    private  String nameBook;
    private  Integer numberPages;
    private  Integer year;
    private Date dateCreate;


    public void setId(int id) {
        this.id = id;

    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getNameBook() {
        return nameBook;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public int getYear() {
        return year;
    }

    public Date getDateCreate() {
        return dateCreate;
    }


    public Bookss(Integer id, String author, String nameBook, Integer numberPages, Integer year, Date dateCreate) {
        this.id = id;
        this.author = author;
        this.nameBook = nameBook;
        this.numberPages = numberPages;
        this.year = year;
        this.dateCreate = dateCreate;
    }

    @Override
    public String toString() {
        return "Bookss{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", nameBook='" + nameBook + '\'' +
                ", numberPages=" + numberPages +
                ", year=" + year +
                ", dateCreate=" + dateCreate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookss bookss = (Bookss) o;
        return Objects.equals(id, bookss.id) &&
                Objects.equals(author, bookss.author) &&
                Objects.equals(nameBook, bookss.nameBook) &&
                Objects.equals(numberPages, bookss.numberPages) &&
                Objects.equals(year, bookss.year) &&
                Objects.equals(dateCreate, bookss.dateCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, nameBook, numberPages, year, dateCreate);
    }
}

