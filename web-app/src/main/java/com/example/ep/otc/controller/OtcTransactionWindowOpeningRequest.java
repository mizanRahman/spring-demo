package com.example.ep.otc.controller;

import lombok.Data;

/**
 * Created by mac on 12/3/16.
 */
@Data
public class OtcTransactionWindowOpeningRequest {
    private String token;
    private String cardId;
    private String serviceId;
    private String serviceGroupId;
    private String atc;
    private String atcExpiryDate;

}
