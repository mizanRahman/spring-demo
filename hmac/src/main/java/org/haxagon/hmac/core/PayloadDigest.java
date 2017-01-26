package org.haxagon.hmac.core;

import java.security.NoSuchAlgorithmException;

/**
 * Created by mac on 1/3/17.
 */
public interface PayloadDigest {
    public byte[] digest(byte[] payload) throws NoSuchAlgorithmException;
}
