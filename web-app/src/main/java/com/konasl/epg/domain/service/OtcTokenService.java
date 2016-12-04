package com.konasl.epg.domain.service;

import com.konasl.epg.domain.core.OtcToken;
import com.konasl.epg.domain.core.OtcTokenId;
import com.konasl.epg.domain.core.Token;

/**
 * Created by mac on 12/3/16.
 */
public interface OtcTokenService {
    OtcToken getOtcToken(Token token);

    OtcToken bind(Token token, OtcTokenId otcTokenId);

    Token getToken(OtcToken token);
}
