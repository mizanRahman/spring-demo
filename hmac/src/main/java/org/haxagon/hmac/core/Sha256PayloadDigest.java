package org.haxagon.hmac.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mac on 1/3/17.
 */
public class Sha256PayloadDigest implements PayloadDigest {
    public static final String PAYLOAD_DIGEST_ALGORITHM = "SHA-256";


    @Override
    public byte[] digest(byte[] payload) {
        try {
            return MessageDigest.getInstance(PAYLOAD_DIGEST_ALGORITHM).digest(payload);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
