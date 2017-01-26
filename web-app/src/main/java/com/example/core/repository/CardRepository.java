package com.example.core.repository;

import com.example.core.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mac on 11/28/15.
 */
public interface CardRepository extends JpaRepository<Card, Long> {
}
