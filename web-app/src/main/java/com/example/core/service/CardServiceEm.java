package com.example.core.service;

import com.example.core.domain.Card;
import com.example.core.repository.CardDao;
import com.example.core.repository.CardRepository;
import org.hibernate.dialect.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Primary
public class CardServiceEm implements CardService {
    @Autowired
    CardDao cardDao;

    @Autowired
    CardRepository cardRepository;


    @Override
    @Transactional
    public Card create(Card card) {
        cardDao.save(card);
        card.setBalance(90909);
        return card;
    }

    @Override
    public Card findOne(long id) {
        return cardRepository.findOne(id);
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
