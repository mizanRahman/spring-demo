package com.example.application;

import com.example.domain.core.OtcTokenId;
import com.example.domain.core.Token;
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
