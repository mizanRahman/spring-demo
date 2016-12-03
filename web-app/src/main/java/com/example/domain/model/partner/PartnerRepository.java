package com.example.domain.model.partner;

import com.example.domain.Partner;

/**
 * Created by mac on 12/3/16.
 */


public interface PartnerRepository {
 // TODO: partner reposity implementation should be prefetched / cached so that it can be fetched quickly
    Partner getPartnerByServiceGroupId(String serviceGroupId);
}
