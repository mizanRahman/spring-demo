package com.konasl.epg.domain.model.card;

import com.konasl.epg.infrastructure.database.BaseEntity;
import lombok.*;

import javax.persistence.*;
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
@ToString
public class Card extends BaseEntity {

    @Column(name = "pan", unique = true, nullable = false, length = 19)
    private String pan;

    @Column(name = "balance")
    private long balance;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    public static Setter setter(Card card) {
        return new Setter(card);
    }

    public static class Setter {

        Card card;

        public Setter(Card card) {
            this.card = card;
        }

        public Setter pan(String pan) {
            card.pan = pan;
            return this;
        }

        public Setter balance(long balance) {
            card.balance = balance;
            return this;
        }

        public Setter expiryDate(Date expiryDate) {
            card.expiryDate = expiryDate;
            return this;
        }
    }
}
