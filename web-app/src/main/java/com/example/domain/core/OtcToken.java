package com.example.domain.core;

import lombok.Getter;
import lombok.Value;

/**
 * Created by mac on 12/3/16.
 */
@Value(staticConstructor = "of")
public class OtcToken {

    OtcTokenId otcTokenId;
    Long activationDateTime;
    Long expiryDateTime;
    Status status;

    @Getter
    public enum Status {
        INACTIVE(0), ACTIVE(1);

        private int statusId;

        Status(int statusId) {
            this.statusId = statusId;
        }


    }
}
