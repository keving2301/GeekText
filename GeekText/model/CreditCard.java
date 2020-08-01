/*
    This class belongs to the Profile Management Feature where Users
    can create Credit Card that belongs to a User and Retrieve a list of cards for
    that user
 */

package com.bookstore.GeekText.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "CreditCards")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CreditCard {

    @Id
    @Column(name = "cardNumber", unique = true, nullable = false)
    private BigInteger cardNumber;
    @Column(name = "expirationDate")
    private String expirationDate;
    @Column(name = "securityCode")
    private Integer securityCode;
    @Column(name = "cardName")
    private String cardName;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    @JsonIgnore
    private User user;

    //For Deserialization
    public CreditCard() {
    }

    //Constructor
    public CreditCard(String cardName, BigInteger cardNumber, String expirationDate, Integer securityCode) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }

    //Getters and Setters

    public BigInteger getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(BigInteger cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(Integer securityCode) {
        this.securityCode = securityCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}


