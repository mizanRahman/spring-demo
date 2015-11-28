package com.example;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by mac on 11/28/15.
 */
@RestController
@RequestMapping("/cards")
public class CardEndPoint {

    @Autowired
    private CardRepository repository;

    @RequestMapping("")
    public List<Card> findAll() {
        Card card = new Card();
        card.setBalance(2222);
        card.setPan("23248543868436783");
        repository.save(card);
        return repository.findAll();
    }
}
