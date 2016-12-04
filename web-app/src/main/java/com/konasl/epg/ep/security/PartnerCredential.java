package com.konasl.epg.ep.security;

import com.konasl.epg.domain.core.PartnerId;
import lombok.Value;

/**
 * Created by mac on 12/3/16.
 */
@Value
public class PartnerCredential {
    PartnerId partnerId;
    String apiKey;
    String apiSecret;
}
