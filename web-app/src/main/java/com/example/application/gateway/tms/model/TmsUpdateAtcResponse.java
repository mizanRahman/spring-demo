package com.example.application.gateway.tms.model;

import lombok.Data;
import lombok.Value;

/**
 * Created by mac on 12/3/16.
 */
@Value
public class TmsUpdateAtcResponse {
    String token;
    String atc;
    String atcExpiry;

}
