package com.konasl.epg.ep.otc.m12.controller;

import com.konasl.epg.ep.model.M12TransactionRequest;
import com.konasl.epg.ep.model.M12TransactionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mac on 11/28/15.
 */
@RestController
@RequestMapping("/m12")
@Slf4j
public class M12PaymentEndPoint {

    @RequestMapping(path = "/transactions/payment", method = RequestMethod.POST)
    public M12TransactionResponse pay(@RequestBody M12TransactionRequest transactionRequest) {

        return M12TransactionResponse.builder()
                .tokenNumber("123456")
                .balance(122L)
                .build();
    }

    @RequestMapping(path = "/coupons/authenricate", method = RequestMethod.POST)
    public M12TransactionResponse search(@RequestBody M12TransactionRequest transactionRequest) {

        return M12TransactionResponse.builder()
                .tokenNumber("123456")
                .balance(122L)
                .build();
    }

    @RequestMapping(path = "/transactions/cancel", method = RequestMethod.POST)
    public M12TransactionResponse cancelPayment(@RequestBody M12TransactionRequest transactionRequest) {

        return M12TransactionResponse.builder()
                .tokenNumber("123456")
                .balance(122L)
                .build();
    }
}
