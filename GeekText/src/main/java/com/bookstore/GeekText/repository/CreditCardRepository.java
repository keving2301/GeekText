package com.bookstore.GeekText.repository;

import com.bookstore.GeekText.model.CreditCard;
import com.bookstore.GeekText.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, BigInteger> {
    List<CreditCard> findByUser(User user);
}


