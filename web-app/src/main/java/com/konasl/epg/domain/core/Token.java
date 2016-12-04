package com.konasl.epg.domain.core;

import lombok.NonNull;
import lombok.Value;


/**
 * Created by mac on 12/3/16.
 */
@Value(staticConstructor = "of")
public class Token {
    @NonNull
    String value;
}
