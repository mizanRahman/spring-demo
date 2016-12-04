package com.konasl.epg.application.impl;

import com.konasl.epg.application.TemporaryTransactionCredential;
import com.konasl.epg.application.TransactionWindowOpenParams;
import com.konasl.epg.application.TransactionWindowOpeningService;
import com.konasl.epg.application.gateway.tms.TmsApiGateway;
import com.konasl.epg.application.gateway.tms.model.TmsUpdateAtcRequest;
import com.konasl.epg.application.gateway.tms.model.TmsUpdateAtcResponse;
import com.konasl.epg.domain.core.OtcTokenId;
import com.konasl.epg.domain.core.Partner;
import com.konasl.epg.domain.core.PartnerId;
import com.konasl.epg.domain.core.Token;
import com.konasl.epg.domain.model.otc.OtcTokenRepository;
import com.konasl.epg.domain.model.partner.PartnerRepository;
import com.konasl.epg.domain.service.OtcTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rx.Observable;

/**
 * Created by mac on 12/3/16.
 */
@Service
@Slf4j
public class TransactionWindowOpeningServiceImpl implements TransactionWindowOpeningService {

    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private TmsApiGateway tmsApiGateway;
    @Autowired
    private OtcTokenService otcTokenService;
    @Autowired
    private OtcTokenRepository otcTokenRepository;

    @Override
    public TemporaryTransactionCredential openTransactionWindow(TransactionWindowOpenParams params) {

        Observable<ResponseEntity<TmsUpdateAtcResponse>> tmsResult =
                tmsApiGateway.updateAtc(
                        TmsUpdateAtcRequest.of(params.getCardId(),
                                params.getAtc(),
                                params.getAtcExpiry()));

        Partner p = partnerRepository.getPartner(PartnerId.of(params.getServiceGroupId()));

        // Now Generate a otcToken using redis sorted set
        OtcTokenId nextOtcTokenId = otcTokenRepository.getNextOtcTokenId(p.getPartnerCode());
        TmsUpdateAtcResponse tmsResponse = tmsResult.toBlocking().first().getBody();
        // TODO: Remove following line: dummy token for test purpose
        tmsResponse.setToken("12333");
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
