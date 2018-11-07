package com.tsystems.services.payment.paymentservice;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;


@Entity
@Table(name="CARD")
public class Card {

    @Id
    @Column(name="CARD_NUMBER")
    private Long cardNumber;

    @Column(name = "CVV")
    private String cvv;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="BALANCE")
    private BigDecimal balance;

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(cardNumber, card.cardNumber) &&
                Objects.equals(cvv, card.cvv) &&
                Objects.equals(userName, card.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardNumber, cvv, userName);
    }
}
