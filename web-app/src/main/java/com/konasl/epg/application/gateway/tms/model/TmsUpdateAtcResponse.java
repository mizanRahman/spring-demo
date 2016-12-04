package com.konasl.epg.application.gateway.tms.model;

import lombok.Data;

/**
 * Created by mac on 12/3/16.
 */
@Data
public class TmsUpdateAtcResponse {
    String token;
    String atc;
    String atcExpiry;

}
