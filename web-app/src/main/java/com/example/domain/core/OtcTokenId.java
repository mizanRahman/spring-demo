package com.example.domain.core;

import lombok.NonNull;
import lombok.Value;

/**
 * Created by mac on 12/3/16.
 */
@Value(staticConstructor = "of")
public class OtcTokenId {
    PartnerId partnerId;
    OtcSequence otcSequence;

    public String getId() {
        return partnerId.getId().concat(otcSequence.getId());
    }
}
