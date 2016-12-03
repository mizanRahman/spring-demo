package com.example.application.impl;

import com.example.application.TemporaryTransactionCredential;
import com.example.application.TransactionWindowOpenParams;
import com.example.application.TransactionWindowOpeningService;
import com.example.application.gateway.tms.TmsApiGateway;
import com.example.application.gateway.tms.model.TmsUpdateAtcRequest;
import com.example.application.gateway.tms.model.TmsUpdateAtcResponse;
import com.example.domain.core.OtcTokenId;
import com.example.domain.core.Token;
import com.example.domain.model.otc.OtcTokenRepository;
import com.example.domain.model.partner.PartnerRepository;
import com.example.domain.service.OtcTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rx.Observable;

/**
 * Created by mac on 12/3/16.
 */
@Service
public class TransactionWindowOpeningServiceImpl implements TransactionWindowOpeningService {

    private PartnerRepository partnerRepository;
    private TmsApiGateway tmsApiGateway;
    private OtcTokenService otcTokenService;
    private OtcTokenRepository otcTokenRepository;

    @Override
    public TemporaryTransactionCredential openTransactionWindow(TransactionWindowOpenParams params) {

        Observable<ResponseEntity<TmsUpdateAtcResponse>> tmsResult =
                tmsApiGateway.updateAtc(
                        TmsUpdateAtcRequest.of(params.getCardId(),
                                params.getAtc(),
                                params.getAtcExpiry()));

        partnerRepository.getPartnerByServiceGroupId(params.getServiceGroupId());

        // Now Generate a otcToken
        OtcTokenId nextOtcTokenId = otcTokenRepository.getNextOtcTokenId();
        TmsUpdateAtcResponse tmsResponse = tmsResult.toBlocking().first().getBody();
        Token token = Token.of(tmsResponse.getToken());

        // should be async and store in redis
        otcTokenService.bind(token, nextOtcTokenId);

        return TemporaryTransactionCredential.builder()
                .token(token)
                .otcTokenId(nextOtcTokenId)
                .atcExpiry(tmsResponse.getAtcExpiry())
                .build();

    }
}
