package com.konasl.epg.domain.core;

import lombok.Value;

/**
 * Created by mac on 12/3/16.
 */
@Value(staticConstructor = "of")
public class Partner {
    PartnerId partnerId;
    PartnerCode partnerCode;
    int otcTokenLength;

    public boolean includePartnerCodeInOtcToken() {
        return partnerCode == null;
    }
}
