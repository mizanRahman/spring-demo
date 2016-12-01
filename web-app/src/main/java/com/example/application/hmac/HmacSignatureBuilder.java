package com.example.application.hmac;

/**
 * Created by mac on 12/1/16.
 */

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Slf4j
public class HmacSignatureBuilder {

    public static final String KONA_HMAC_SHA256 = "KONA-HMAC-SHA256";
    public static final String PAYLOAD_DIGEST_ALGORITHM = "SHA-256";
    private static final byte DELIMITER = '\n';
    public static final String HMAC_SHA_256 = "HmacSHA256";

    private String scheme;
    private String algorithm = HMAC_SHA_256;
    private String method;
    private String uri;
    private String queryString;
    private String contentType;


    private byte[] apiSecret;
    private byte[] payload;

    public String getAlgorithm() {
        return algorithm;
    }

    public HmacSignatureBuilder algorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public HmacSignatureBuilder scheme(String scheme) {
        this.scheme = scheme;
        return this;
    }


    public HmacSignatureBuilder method(String method) {
        this.method = method;
        return this;
    }

    public HmacSignatureBuilder uri(String uri) {
        this.uri = uri;
        return this;
    }

    public HmacSignatureBuilder queryString(String queryString) {
        if (queryString == null) {
            this.queryString = "";
        } else {
            try {
                this.queryString = new URI(queryString).normalize().toString();
            } catch (URISyntaxException e) {
                throw new RuntimeException("invalid query string: " + e.getMessage(), e);
            }
        }
        return this;
    }

    public HmacSignatureBuilder contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public HmacSignatureBuilder apiSecret(byte[] secretBytes) {
        this.apiSecret = secretBytes;
        return this;
    }

    public HmacSignatureBuilder apiSecret(String secretString) {
        this.apiSecret = secretString.getBytes(StandardCharsets.UTF_8);
        return this;
    }


    public HmacSignatureBuilder payload(byte[] payloadBytes) {
        this.payload = payloadBytes;
        return this;
    }

    public byte[] build() {

        Objects.requireNonNull(algorithm, "algorithm");
        Objects.requireNonNull(scheme, "scheme");
        Objects.requireNonNull(method, "method");
        Objects.requireNonNull(uri, "uri");
        Objects.requireNonNull(queryString, "queryString");
        Objects.requireNonNull(contentType, "contentType");
        Objects.requireNonNull(payload, "payload");

        try {
            final Mac digest = Mac.getInstance(algorithm);
            SecretKeySpec secretKey = new SecretKeySpec(apiSecret, algorithm);
            digest.init(secretKey);
            digest.update(scheme.getBytes(StandardCharsets.UTF_8));
            digest.update(DELIMITER);
            digest.update(method.getBytes(StandardCharsets.UTF_8));
            digest.update(DELIMITER);
            digest.update(uri.getBytes(StandardCharsets.UTF_8));
            digest.update(DELIMITER);
            digest.update(queryString.getBytes(StandardCharsets.UTF_8));
            digest.update(DELIMITER);
            digest.update(contentType.getBytes(StandardCharsets.UTF_8));
            digest.update(DELIMITER);

            String digestPayload = DatatypeConverter.printHexBinary(
                    MessageDigest.getInstance(PAYLOAD_DIGEST_ALGORITHM).digest(payload));
            log.info("digest of Payload = {}", digestPayload);
            digest.update(digestPayload.getBytes(StandardCharsets.UTF_8));
            digest.update(DELIMITER);

            final byte[] signatureBytes = digest.doFinal();
            digest.reset();
            return signatureBytes;
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Can't create signature: " + e.getMessage(), e);
        }
    }

    public boolean isHashEquals(byte[] expectedSignature) {
        final byte[] signature = build();
        return MessageDigest.isEqual(signature, expectedSignature);
    }

    public String buildAsHexString() {
        return DatatypeConverter.printHexBinary(build());
    }

    public String buildAsBase64String() {
        return DatatypeConverter.printBase64Binary(build());
    }
}

