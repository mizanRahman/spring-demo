package com.example.application.hmac;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mac on 12/1/16.
 */
@Slf4j
class HmacUtil {

    private static final Pattern AUTHORIZATION_HEADER_PATTERN = Pattern.compile("^(\\S+) (\\S+):([\\S]+)$");

    static AuthHeader getAuthHeader(HttpServletRequest request) {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization Header = {}", authHeader);

        if (authHeader == null) {
            // absent authorization header
            log.error("absent authorization header");
            return null;
        }

        final Matcher authHeaderMatcher = AUTHORIZATION_HEADER_PATTERN.matcher(authHeader.trim());
        if (!authHeaderMatcher.matches()) {
            // invalid authorization token
            log.error("invalid authorization token");
            return null;
        }

        final String algorithm = authHeaderMatcher.group(1);
        final String apiKey = authHeaderMatcher.group(2);
        final String receivedDigest = authHeaderMatcher.group(3);

        return new AuthHeader(algorithm, apiKey, DatatypeConverter.parseBase64Binary(receivedDigest));
    }
}


