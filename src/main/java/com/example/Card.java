package com.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by mac on 11/28/15.
 */
@Entity
@Data
public class Card {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @Column(name = "pan", unique = true, nullable = false, length = 16)
    private String pan;

    @Column(name = "balance")
    private long balance;
}
