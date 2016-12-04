package com.konasl.epg.ep.otc.controller;

import lombok.Builder;
import lombok.Data;

/**
 * Created by mac on 12/3/16.
 */
@Builder
@Data
public class OtcTransactionWindowOpeningResponse {
    String token;
    String otcTokenId;
    String atcExpiry;
}
