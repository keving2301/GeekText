package com.bookstore.GeekText.resource;

import com.bookstore.GeekText.model.CreditCard;
import com.bookstore.GeekText.model.User;
import com.bookstore.GeekText.repository.CreditCardRepository;
import com.bookstore.GeekText.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/geektext")
class CreditCardResource {

    @Autowired
    CreditCardRepository cardsRepository;

    @Autowired
    UserRepository userRepository;

    //Displays List of All Credit Cards on Database
    @GetMapping("/cards")
    public List<CreditCard> getAllCreditCards() {
        return cardsRepository.findAll();
    }

    //Displays CreditCard By Username Search
    @GetMapping("/users/{username}/cards")
    public List<CreditCard> getCardsByUsername(@PathVariable String username) throws NotFoundException {
        if (userRepository.findById(username).isEmpty()) {
            throw new NotFoundException("User '" + username + "' not found");
        }
        return cardsRepository.findByUser(userRepository.findById(username).get());
    }

    //Creates New Card for User
    @PostMapping("/users/{username}/cards")
    public CreditCard loadCard(@PathVariable String username, @RequestBody CreditCard creditCard) throws NotFoundException {

        if (userRepository.findById(username).isEmpty()) {
            throw new NotFoundException("User '" + username + "' not found");
        }
        if (!cardsRepository.findById(creditCard.getCardNumber()).isEmpty()) {
            throw new NotFoundException("Credit Card '" + creditCard.getCardNumber() + "' already on File");
        }

        //Setting up Card expirationDate correctly
        DateTimeFormatter MonthFormatter = DateTimeFormatter.ofPattern("MM/yy");
        //String creditCardExpiryDateString = "11/21";
        try {
            YearMonth lastValidMonth = YearMonth.parse(creditCard.getExpirationDate(), MonthFormatter);
            if (YearMonth.now(ZoneId.systemDefault()).isAfter(lastValidMonth)) {
                throw new IllegalArgumentException("Credit card has expired");
            }
        } catch (DateTimeParseException dtpe) {
            throw new IllegalArgumentException("Credit Card expiration date is Invalid." +
                    "Correct Format is MM/yy");
        }

        //Saving the new Credit Card
        User user = userRepository.findById(username).get();
        user.getCreditCard().add(creditCard);
        creditCard.setUser(userRepository.findById(username).get());
        userRepository.save(user);
        return cardsRepository.save(creditCard);
    }

    //Deletes an Existing Card By cardNumber
    @DeleteMapping("/users/{username}/cards/{cardNumber}")
    public String deleteCard(@PathVariable String username, @PathVariable BigInteger cardNumber) throws NotFoundException {

        if (userRepository.findById(username).isEmpty()) {

            throw new NotFoundException("User '" + username + "' not found");

        } else if (cardsRepository.findById(cardNumber).isEmpty()) {

            throw new NotFoundException("Credit Card not found");
        }

        cardsRepository.delete(cardsRepository.findById(cardNumber).get());
        return "Credit Card '" + cardNumber + "' has been deleted";

    }

    //Delete all credit cards by username************
}