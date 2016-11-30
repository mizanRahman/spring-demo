package com.example.ep.controller;

import com.example.integration.KonaMoneyTransactionGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by mac on 12/1/16.
 */
@RestController
@RequestMapping("/m12/transaction")
@Slf4j
public class TransactionEndPoint {

    @Autowired
    KonaMoneyTransactionGateway transactionGateway;


    @RequestMapping("/payment")
    public String tx() {
        byte[] message = "<amount>10</amount>".getBytes(Charset.defaultCharset());
        log.info("sending txn request over tcp");
        byte[] response = transactionGateway.send(message);
        log.info("{} = {}",
                DatatypeConverter.printHexBinary(message),
                DatatypeConverter.printHexBinary(response));
        log.info("eq: {}", Arrays.equals(message, response));

        return new String(response, Charset.defaultCharset());
    }
}
