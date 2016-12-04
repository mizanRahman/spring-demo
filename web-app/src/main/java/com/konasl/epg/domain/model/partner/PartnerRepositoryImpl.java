package com.konasl.epg.domain.model.partner;

import com.konasl.epg.domain.core.Partner;
import com.konasl.epg.domain.core.PartnerCode;
import com.konasl.epg.domain.core.PartnerId;
import org.springframework.stereotype.Repository;

/**
 * Created by mac on 12/4/16.
 */
@Repository
public class PartnerRepositoryImpl implements PartnerRepository {

    private final Partner partner;

    public PartnerRepositoryImpl() {

        this.partner = Partner.of(PartnerId.of("101"), PartnerCode.of("01"), 9);
    }

    @Override
    public Partner getPartner(PartnerId partnerId) {
        // will be fetched from configuration
        return partner;
    }
}
