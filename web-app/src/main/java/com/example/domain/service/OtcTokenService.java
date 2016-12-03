package com.example.domain.service;

import com.example.domain.core.OtcToken;
import com.example.domain.core.OtcTokenId;
import com.example.domain.core.Token;

/**
 * Created by mac on 12/3/16.
 */
public interface OtcTokenService {
    OtcToken getOtcToken(Token token);
    OtcToken bind(Token token, OtcTokenId otcTokenId);

    Token getToken(OtcToken token);
}
