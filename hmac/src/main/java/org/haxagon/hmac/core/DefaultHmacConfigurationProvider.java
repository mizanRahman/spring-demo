package org.haxagon.hmac.core;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created by mac on 1/3/17.
 */
public class DefaultHmacConfigurationProvider implements HmacConfigurationProvider {

    public static final String KONA_HMAC_SHA256 = "KONA-HMAC-SHA256";
    private static final byte DELIMITER = '\n';
    public static final String HMAC_SHA_256 = "HmacSHA256";


    @Override
    public String getScheme() {
        return KONA_HMAC_SHA256;
    }

    @Override
    public byte getDelimeter() {
        return DELIMITER;
    }

    @Override
    public String getAlgorithm() {
        return HMAC_SHA_256;
    }

    @Override
    public PayloadDigest getPayloadDigest() {
        return new Sha256PayloadDigest();
    }

    @Override
    public PayloadEncoder getpayloadEncoder() {
        return new HexPayloadEncoder();
    }

    @Override
    public Key getApiSecret(byte[] secret) {
        return new SecretKeySpec(secret, getAlgorithm());
    }
}
