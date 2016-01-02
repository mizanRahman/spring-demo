package com.example.ep.controller;

import com.example.core.domain.Card;
import com.example.core.domain.errorcode.ReasonCodes;
import com.example.core.domain.errorcode.Subjects;
import com.example.core.service.CardService;
import com.example.ep.exception.DomainException;
import com.example.ep.exception.ResourceNotFoundException;
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
        Card savedCard = cardService.create(card);
        log.info("card saved. id: {}", savedCard.getId());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Card update(@PathVariable long id, @RequestBody Card card) {
        Card cardToUpdate = cardService.findOne(id);
        Card.setter(cardToUpdate)
                .pan(card.getPan())
                .balance(card.getBalance())
                .expiryDate(card.getExpiryDate());

        try {
            return cardService.update(cardToUpdate);

        } catch (ResourceNotFoundException e) {
            throw new DomainException(ReasonCodes.NO_RESULT, Subjects.MAP.Add_Card, e.getMessage(), e);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        Card card = cardService.findOne(id);
        cardService.delete(card);
    }

    @RequestMapping("/schema")
    public Card schema() {
        return Card.builder().build();
    }

}
