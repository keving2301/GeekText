package com.bookstore.GeekText.resource;

import java.util.List;
import java.util.Optional;

import com.bookstore.GeekText.model.AuthorID;
import com.bookstore.GeekText.model.Authors;
import com.bookstore.GeekText.model.Books;
import com.bookstore.GeekText.repository.AuthorsRepository;
import com.bookstore.GeekText.repository.BooksRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/geektext")
public class BooksResource {
    
    @Autowired
    BooksRepository booksrepo;
    
    @Autowired
    AuthorsRepository authorsrepo;

    //Displays a List of All Books on Database
    @GetMapping("/books")
    public List<Books> getAll() {
        return booksrepo.findAll();
    }

    //Displays a List of Books by ISBN (primary key of Books class)
    @GetMapping("/books/{isbn}")
    public Books getById(@PathVariable Long isbn) throws NotFoundException {
        Optional<Books> result = booksrepo.findById(isbn);
        if (result.isEmpty()) {
            throw new NotFoundException("Book with ISBN " + isbn + " is not found!");
        }
        return result.get();
    }

    //Creates a New Book
    @PostMapping("/books")
    public Books add(@RequestBody final Books book) throws NotFoundException {
        Authors author = book.getAuthor();
        AuthorID name = new AuthorID(author.getFirstName(), author.getLastName());
        Optional<Authors> result = authorsrepo.findById(name);
        if (result.isEmpty()) {
            throw new NotFoundException("Author of this book has not been created. Create author first, then this book!");
        }

        result.get().getBooks().add(book);
        book.setAuthor(result.get());
        authorsrepo.save(result.get());
        return booksrepo.save(book);
    }

    //Updates a Current Book
    @PutMapping(value = "/books")
    public String update(@RequestBody final Books book) {
        booksrepo.save(book);
        return "Book with ISBN " + book.getIsbn() + " is updated!";
    }

    //Deletes a Book by ISBN
    @DeleteMapping(value = "/books/{isbn}")
    public String delete(@PathVariable Long isbn) throws NotFoundException {
        Optional<Books> result = booksrepo.findById(isbn);
        if(result.isEmpty()) {
            throw new NotFoundException("Book with ISBN " + isbn + " does not exist!");
        }
        booksrepo.delete(result.get());
        return "Book with ISBN " + isbn + " is deleted!";
    }
}
