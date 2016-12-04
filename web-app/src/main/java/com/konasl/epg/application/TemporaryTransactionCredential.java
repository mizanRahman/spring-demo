package com.konasl.epg.application;

import com.konasl.epg.domain.core.OtcTokenId;
import com.konasl.epg.domain.core.Token;
import lombok.Builder;
import lombok.Data;

/**
 * Created by mac on 12/3/16.
 */
@Builder
@Data
public class TemporaryTransactionCredential {
    Token token;
    OtcTokenId otcTokenId;
    String atcExpiry;
}
