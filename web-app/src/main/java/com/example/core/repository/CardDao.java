package com.example.core.repository;

import com.example.core.domain.Card;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class CardDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void save(Card card) {
        entityManager.persist(card);
    }
}
