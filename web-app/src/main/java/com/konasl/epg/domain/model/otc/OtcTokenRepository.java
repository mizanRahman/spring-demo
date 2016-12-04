package com.konasl.epg.domain.model.otc;

import com.konasl.epg.domain.core.OtcTokenId;
import com.konasl.epg.domain.core.PartnerCode;
import com.konasl.epg.domain.core.PartnerId;

/**
 * Created by mac on 12/3/16.
 */
public interface OtcTokenRepository {

    OtcTokenId getNextOtcTokenId(PartnerCode partnerCode);
}
