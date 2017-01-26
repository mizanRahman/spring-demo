package org.haxagon.hmac.core;

/**
 * Created by mac on 12/31/16.
 */
public interface PayloadEncoder {
    public String encode(byte[] payload);
    public byte[] decode(String payload);
}
