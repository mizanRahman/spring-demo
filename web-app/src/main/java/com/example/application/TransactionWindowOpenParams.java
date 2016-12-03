package com.example.application;

import com.example.domain.core.Token;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

/**
 * Created by mac on 12/3/16.
 */
@Value(staticConstructor = "of")
public class TransactionWindowOpenParams {
    Token token;
    String cardId;
    String serviceId;
    String serviceGroupId;
    String atc;
    String atcExpiry;
}
