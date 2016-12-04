package com.konasl.epg.domain.core;

import lombok.NonNull;
import lombok.Value;

/**
 * Created by mac on 12/4/16.
 */
@Value(staticConstructor = "of")
public class PartnerCode {
    @NonNull
    String code;
}
