package com.example.ep.security;

import com.example.domain.core.PartnerId;
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
