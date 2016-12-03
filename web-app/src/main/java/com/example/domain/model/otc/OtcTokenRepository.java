package com.example.domain.model.otc;

import com.example.domain.core.OtcToken;
import com.example.domain.core.OtcTokenId;

/**
 * Created by mac on 12/3/16.
 */
public interface OtcTokenRepository {

    OtcTokenId getNextOtcTokenId();
}
