package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mac on 11/28/15.
 */
@RestController
@RequestMapping("/cards")
@Slf4j
public class CardEndPoint {

    @Autowired
    private CardService cardService;

    @RequestMapping
    public List<Card> findAll() {
        List<Card> cards = cardService.findAll();
        log.info("total {} cards found", cards.size());
        return cards;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Card card) {
        Card savedCard = cardService.save(card);
        log.info("card saved. id: {}", savedCard.getId());
    }

    @RequestMapping("/schema")
    public Card schema() {
        return Card.builder().build();
    }

}
