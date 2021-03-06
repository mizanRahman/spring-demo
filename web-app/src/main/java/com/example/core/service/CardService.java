package com.example.core.service;

import com.example.core.domain.Card;

import java.util.List;

/**
 * Created by mac on 11/28/15.
 */
public interface CardService {

    Card create(Card card);

    Card findOne(long id);

    List<Card> findAll();

    Card update(Card card);

    void delete(Card card);

}
