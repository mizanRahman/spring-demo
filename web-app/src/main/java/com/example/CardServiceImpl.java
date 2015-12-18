package com.example;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mac on 11/28/15.
 */
@Repository
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card save(Card card) {
        card.setExpiryDate(DateTime.now().plusDays(5).toDate());
        return cardRepository.save(card);
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();

    }
}
