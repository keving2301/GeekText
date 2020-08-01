package com.bookstore.GeekText.resource;

import com.bookstore.GeekText.model.AuthorID;
import com.bookstore.GeekText.model.Authors;
import com.bookstore.GeekText.model.Books;
import com.bookstore.GeekText.repository.AuthorsRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/geektext")
public class AuthorsResource {
    
    @Autowired
    AuthorsRepository authorsrepo;
    
    @GetMapping(value = "/authors/all")
    public List<Authors> getAll() {
        return authorsrepo.findAll();
    }

    @GetMapping(value = "/authors")
    public List<Books> getAll(@RequestBody final Authors author) throws NotFoundException {
       AuthorID name = new AuthorID(author.getFirstName(), author.getLastName());
       Optional<Authors> result = authorsrepo.findById(name);
       if (result.isEmpty()) {
           throw new NotFoundException("This author does not exist!");
       }
       return result.get().getBooks();
    }
    
    @PostMapping(value = "/authors")
    public Authors add(@RequestBody final Authors author) {
       author.setFirstName(author.getFirstName());
       author.setLastName(author.getLastName());
       return authorsrepo.save(author);
    }
    
    @PutMapping(value = "/authors")
    public String update(@RequestBody final Authors author) {
        author.setFirstName(author.getFirstName());
        author.setLastName(author.getLastName());
        authorsrepo.save(author);
        return "Author " + author.getFirstName() + " " + author.getLastName() + " is updated!";
    }
    
    @DeleteMapping(value = "/authors")
    public String delete(@RequestBody final Authors author) throws NotFoundException {
       AuthorID name = new AuthorID(author.getFirstName(), author.getLastName());
       Optional<Authors> result = authorsrepo.findById(name);
       if (result.isEmpty()) {
           throw new NotFoundException("This author does not exist!");
       }
       authorsrepo.delete(result.get());
       return "Author " + author.getFirstName() + " " + author.getLastName() + " is deleted!";
    }
}
