package org.haxagon.hmac.core;

import javax.xml.bind.DatatypeConverter;

/**
 * Created by mac on 12/31/16.
 */
public class HexPayloadEncoder implements PayloadEncoder {
    @Override
    public String encode(byte[] payload) {
        return DatatypeConverter.printHexBinary(payload);
    }

    @Override
    public byte[] decode(String payload) {
        return DatatypeConverter.parseHexBinary(payload);
    }
}
