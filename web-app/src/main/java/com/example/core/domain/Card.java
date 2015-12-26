package com.example.core.domain;

import com.example.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by mac on 11/28/15.
 */
@Entity(name = "cards")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card extends BaseEntity {

    @Column(name = "pan", unique = true, nullable = false, length = 19)
    private String pan;

    @Column(name = "balance")
    private long balance;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
}
