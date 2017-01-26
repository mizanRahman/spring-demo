package org.haxagon.hmac.core;

import lombok.Data;
import lombok.ToString;

/**
 * Created by mac on 12/1/16.
 */
@Data
@ToString
public class AuthHeader {
    private final String scheme;
    private final String apiKey;
    private final byte[] signature;
}

