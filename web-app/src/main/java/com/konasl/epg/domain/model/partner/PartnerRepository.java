package com.konasl.epg.domain.model.partner;

import com.konasl.epg.domain.core.Partner;
import com.konasl.epg.domain.core.PartnerId;

/**
 * Created by mac on 12/3/16.
 */


public interface PartnerRepository {
 // TODO: partner reposity implementation should be prefetched / cached so that it can be fetched quickly
    Partner getPartner(PartnerId partnerId);
}
