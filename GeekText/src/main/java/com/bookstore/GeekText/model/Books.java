package com.bookstore.GeekText.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Books {
    
    @Id
    @GeneratedValue
    @Column(name = "isbn", nullable = false, unique = true)
    private Long isbn;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "genre")
    private String genre;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "year")
    private int year;
    @Column(name = "soldCopy")
    private long soldCopy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "firstName", referencedColumnName = "firstName", nullable = false),
        @JoinColumn(name = "lastName", referencedColumnName = "lastName", nullable = false)
        })
    Authors author;

    //Constructor
    public Books() {
    }


    //Getters and Setters
    public Long getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
    
    public Authors getAuthor() {
        return author;
    }
    
    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public long getSoldCopy() {
        return soldCopy;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
   
    public void setAuthor(Authors author) {
        this.author = author;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSoldCopy(int soldCopy) {
        this.soldCopy = soldCopy;
    }


}
