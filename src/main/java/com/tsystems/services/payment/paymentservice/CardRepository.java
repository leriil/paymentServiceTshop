package com.tsystems.services.payment.paymentservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardRepository extends CrudRepository<Card, Long>{
    Card findByCardNumber(Long cardNumber);
    Card findByCardNumberAndCvvAndUserName(Long cardNumber, String cvv, String userName);
}
