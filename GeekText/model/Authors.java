package com.bookstore.GeekText.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@IdClass(AuthorID.class)
public class Authors {
   
    @Id
    @Column(name = "firstName")
    private String firstName;
    @Id
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "biography")
    private String biography;
    @Column(name = "publisher")
    private String publisher;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Books> books;
   
    //Constructor
    public Authors() {
    }

    //Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Books> getBooks() {
        return books;
    }
  
    public String getBiography() {
        return biography;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
  
    public void setBooks(List<Books> books) {
        this.books = books;
    }
}

