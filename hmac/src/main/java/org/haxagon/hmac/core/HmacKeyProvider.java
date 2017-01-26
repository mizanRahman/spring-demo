package org.haxagon.hmac.core;

import java.security.Key;

/**
 * Created by mac on 1/3/17.
 */
public interface HmacKeyProvider {
    Key getKey();
}
