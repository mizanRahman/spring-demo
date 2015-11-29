package com.example;

import java.util.List;

/**
 * Created by mac on 11/28/15.
 */
public interface CardService {

    Card save(Card card);

    List<Card> findAll();

}
