package com.example.ep.otc.controller;

import com.example.application.TransactionWindowOpenParams;
import com.example.application.TransactionWindowOpeningService;
import com.example.domain.core.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mac on 12/3/16.
 */
@Slf4j
@RestController
@RequestMapping("/internal/otc")
public class OtcTransactionWindowOpeningEndPoint {

    @Autowired
    private TransactionWindowOpeningService transactionWindowOpeningService;

    @RequestMapping(path = "/prepare-for-txn", method = RequestMethod.POST)
    public ResponseEntity openTransactionWindow(@RequestBody OtcTransactionWindowOpeningRequest request) {

        transactionWindowOpeningService.openTransactionWindow(
                TransactionWindowOpenParams.of(
                        Token.of(request.getToken()),
                        request.getCardId(),
                        request.getServiceId(),
                        request.getServiceGroupId(),
                        request.getAtc(),
                        request.getAtcExpiryDate()));

        return ResponseEntity.ok("otcSeq");
    }
}
