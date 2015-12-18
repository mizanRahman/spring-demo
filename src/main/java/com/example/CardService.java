package com.example;

import java.util.List;

/**
 * Created by mac on 11/28/15.
 */
public interface CardService {

    public Card save(Card card);

    public List<Card> findAll();

}
