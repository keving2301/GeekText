package com.bookstore.GeekText.resource;

import com.bookstore.GeekText.model.User;
import com.bookstore.GeekText.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/geektext")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    //Displays User By Username Search
    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable String username) {
        Optional<User> users = userRepository.findById(username);
        if (users.isPresent()) {
            return users.get();
        } else {
            throw new RuntimeException("User '" + username + "' not found");
        }
    }

    //Displays List of All Users on Database
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Creates a New User
    @PostMapping("/users")
    public User loadUser(@RequestBody final User user) throws NotFoundException {
        if(userRepository.findById(user.getUsername()).isPresent()){
            throw new NotFoundException("Username '" + user.getUsername() + "' is already taken");
        }
        user.setEmail(user.getUsername());
        return userRepository.save(user);
    }

    //Creates a List of New Users
    @PostMapping("/users/all")
    public List<User> loadAllUsers(@RequestBody final List<User> users) {
        return userRepository.saveAll(users);
    }

    //Updates an Existing User
    @PutMapping("/users/{username}")
    public User updateUser(@PathVariable String username, @RequestBody User user) throws NotFoundException {

        if(!userRepository.findById(username).isPresent()){
            throw new NotFoundException("User '" + username + "' not found");
        }

        //Cannot modify username
        user.setUsername(username);
        //Make email te same as user. Cannot be modified by User
        user.setEmail(username);

        return userRepository.save(user);
    }

    //Deletes an Existing User
    @DeleteMapping("/users/{username}")
    public String deleteUser(@PathVariable String username) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return "User '" + username + "' has been deleted";
        } else {
            throw new RuntimeException("User '" + username + "' not found");
        }
    }

}
