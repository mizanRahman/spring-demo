package com.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mac on 11/28/15.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Card extends BaseEntity {

    @Column(name = "pan", unique = true, nullable = false, length = 16)
    private String pan;

    @Column(name = "balance")
    private long balance;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
}
