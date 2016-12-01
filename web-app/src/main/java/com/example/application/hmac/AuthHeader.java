package com.example.application.hmac;

import lombok.Data;

/**
 * Created by mac on 12/1/16.
 */
@Data
public class AuthHeader {
    private final String scheme;
    private final String apiKey;
    private final byte[] signature;
}

