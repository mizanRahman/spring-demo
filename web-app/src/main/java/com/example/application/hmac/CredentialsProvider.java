package com.example.application.hmac;

/**
 * Created by mac on 12/1/16.
 */
public interface CredentialsProvider {
    byte[] getApiSecret(String apiKey);
}
