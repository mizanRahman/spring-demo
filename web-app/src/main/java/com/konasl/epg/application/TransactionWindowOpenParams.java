package com.konasl.epg.application;

import com.konasl.epg.domain.core.Token;
import lombok.Value;

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
