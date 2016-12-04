package com.konasl.epg.domain.model.otc;

import com.konasl.epg.domain.core.*;
import org.springframework.stereotype.Repository;

/**
 * Created by mac on 12/4/16.
 */
@Repository
public class OtcTokenRepositoryImpl implements OtcTokenRepository {
    @Override
    public OtcTokenId getNextOtcTokenId(PartnerCode partnerCode) {
        // TODO: write real logic to generate otcSequence using redis
        return OtcTokenId.of(partnerCode, OtcSequence.of("1234567"));
    }
}
