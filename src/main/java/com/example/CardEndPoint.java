package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("------------------findAll called----------------");
        return cardService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Card card) {
        cardService.save(card);
    }

    @RequestMapping("/schema")
    public Card schema() {
        return new Card();
    }
}
