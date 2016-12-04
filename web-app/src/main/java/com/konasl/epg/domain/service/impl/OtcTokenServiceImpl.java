package com.konasl.epg.domain.service.impl;

import com.konasl.epg.domain.core.OtcToken;
import com.konasl.epg.domain.core.OtcTokenId;
import com.konasl.epg.domain.core.Token;
import com.konasl.epg.domain.service.OtcTokenService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

/**
 * Created by mac on 12/4/16.
 */
@Service
public class OtcTokenServiceImpl implements OtcTokenService {
    @Override
    public OtcToken getOtcToken(Token token) {
        return null;
    }

    @Override
    public OtcToken bind(@NonNull Token token, @NonNull OtcTokenId otcTokenId) {

        return null;
    }

    @Override
    public Token getToken(OtcToken token) {
        return null;
    }
}
