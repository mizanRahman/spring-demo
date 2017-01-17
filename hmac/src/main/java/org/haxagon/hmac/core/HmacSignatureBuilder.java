package org.haxagon.hmac.core;

/**
 * Created by mac on 12/1/16.
 */

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.validation.constraints.NotNull;
import javax.xml.bind.DatatypeConverter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Slf4j
public class HmacSignatureBuilder {

    private String method;
    private String uri;
    private String queryString;
    private String contentType;

    private byte[] apiSecret;
    private byte[] payload;

    private HmacConfigurationProvider hmacConfig;

    public HmacSignatureBuilder(HmacConfigurationProvider provider) {
        this.hmacConfig = provider;
    }

    public HmacSignatureBuilder() {
        this.hmacConfig = new DefaultHmacConfigurationProvider();
    }

    public static HmacSignatureBuilder buulder() {
        return new HmacSignatureBuilder();
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
        this.apiSecret = getBytes(secretString);
        return this;
    }


    public HmacSignatureBuilder payload(byte[] payloadBytes) {
        this.payload = payloadBytes;
        return this;
    }

    public void addToDigest(String... params) throws NoSuchAlgorithmException {
        Mac digest = Mac.getInstance(hmacConfig.getAlgorithm());
        for (String p : params) {
            digest.update(getBytes(p));
        }
        digest.update(hmacConfig.getDelimeter());
    }

    public void buildDigest() throws NoSuchAlgorithmException {
        addToDigest(hmacConfig.getScheme(), method, uri, queryString, contentType);
        String digestPayload = hmacConfig.getpayloadEncoder().encode(hmacConfig.getPayloadDigest().digest(payload));
        log.info("digest of Payload = {}", digestPayload);
        addToDigest(digestPayload);
    }

    public static byte[] getBytes(@NotNull String scheme) {
        return scheme.getBytes(StandardCharsets.UTF_8);
    }


    public static void requireNotNull(Object... values) {
        for (Object v : values) {
            Objects.requireNonNull(v);
        }
    }

    public final byte[] build() {
        requireNotNull(hmacConfig.getAlgorithm(), hmacConfig.getScheme(), method, uri, queryString, contentType, payload);
        try {
            final Mac digest = Mac.getInstance(hmacConfig.getAlgorithm());
            Key key = hmacConfig.getApiSecret(apiSecret);
            digest.init(key);
            buildDigest();
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

