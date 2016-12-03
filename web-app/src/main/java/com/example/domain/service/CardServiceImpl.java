package com.example.domain.service;

import com.example.domain.model.card.Card;
import com.example.domain.model.card.CardRepository;
import com.example.ep.exception.ResourceNotFoundException;
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
    public Card create(Card card) {
        card.setExpiryDate(DateTime.now().plusDays(5).toDate());
        return cardRepository.save(card);

    }

    @Override
    public Card findOne(long id) {
        Card card = cardRepository.findOne(id);
        if (card == null) {
            throw new ResourceNotFoundException(String.format("No Card Found for id: %d", id));
        }
        return card;
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card update(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public void delete(Card card) {
        cardRepository.delete(card);
    }
}
