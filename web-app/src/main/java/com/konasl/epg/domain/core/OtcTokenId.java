package com.konasl.epg.domain.core;

import lombok.NonNull;
import lombok.Value;

/**
 * Created by mac on 12/3/16.
 */
@Value(staticConstructor = "of")
public class OtcTokenId {

    @NonNull
    PartnerCode partnerCode;

    @NonNull
    OtcSequence otcSequence;

    public String getId() {
        return partnerCode.getCode().concat(otcSequence.getId());
    }
}
