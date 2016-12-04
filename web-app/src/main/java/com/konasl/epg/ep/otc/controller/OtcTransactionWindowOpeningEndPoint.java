package com.konasl.epg.ep.otc.controller;

import com.konasl.epg.application.TemporaryTransactionCredential;
import com.konasl.epg.application.TransactionWindowOpenParams;
import com.konasl.epg.application.TransactionWindowOpeningService;
import com.konasl.epg.domain.core.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public OtcTransactionWindowOpeningResponse openTransactionWindow(@RequestBody OtcTransactionWindowOpeningRequest request) {

        TemporaryTransactionCredential credential = transactionWindowOpeningService.openTransactionWindow(
                TransactionWindowOpenParams.of(
                        Token.of(request.getToken()),
                        request.getCardId(),
                        request.getServiceId(),
                        request.getServiceGroupId(),
                        request.getAtc(),
                        request.getAtcExpiryDate()));

        return OtcTransactionWindowOpeningResponse.builder()
                .token(credential.getToken().getValue())
                .otcTokenId(credential.getOtcTokenId().getId())
                .atcExpiry(credential.getAtcExpiry())
                .build();
    }


}
