package com.example.application.gateway.tms.model;

import lombok.Value;

/**
 * Created by mac on 12/3/16.
 */
@Value(staticConstructor = "of")
public class TmsUpdateAtcRequest {
    String cardId;
    String atc;
    String atcExpiry;
}
