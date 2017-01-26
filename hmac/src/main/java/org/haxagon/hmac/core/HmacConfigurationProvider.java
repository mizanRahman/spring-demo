package org.haxagon.hmac.core;

import java.security.Key;

/**
 * Created by mac on 1/3/17.
 */
public interface HmacConfigurationProvider {

    String getScheme();
    byte getDelimeter();
    String getAlgorithm();
    PayloadDigest getPayloadDigest();
    PayloadEncoder getpayloadEncoder();

    Key getApiSecret(byte[] secret);

}
