package com.example.integration;

import org.springframework.integration.annotation.Gateway;

/**
 * Created by mac on 12/1/16.
 */

public interface KonaMoneyTransactionGateway {

    byte[] send(byte[] text);
}
