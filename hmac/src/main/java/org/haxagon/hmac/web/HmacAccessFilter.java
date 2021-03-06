package org.haxagon.hmac.web;

import lombok.extern.slf4j.Slf4j;
import org.haxagon.hmac.core.AuthHeader;
import org.haxagon.hmac.core.CredentialsProvider;
import org.haxagon.hmac.core.HmacSignatureBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Restricts access to resource if HMAC signature is not valid.
 * <p/>
 * This filter does not provide Spring SecurityContext down to filter chain.
 */
@Slf4j
public class HmacAccessFilter extends OncePerRequestFilter {

    @Autowired
    private CredentialsProvider credentialsProvider;

    private HmacProperties hmacProperties;

    public HmacAccessFilter(HmacProperties hmacProperties) {
        this.hmacProperties = hmacProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest requestWrapper;
        if (hmacProperties.isEnabled()) {
            requestWrapper = authenticate(request, response);
            if (requestWrapper == null) return;
        } else {
            requestWrapper = request;
        }

        filterChain.doFilter(requestWrapper, response);
    }

    private CachingRequestWrapper authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final AuthHeader authHeader = HmacUtil.getAuthHeader(request);
        log.info("inside hmac access filter: authHeader={}", authHeader);

        if (authHeader == null) {
            // invalid authorization token
            log.warn("Authorization header is missing");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        final String apiKey = authHeader.getApiKey();

        final byte[] apiSecret = credentialsProvider.getApiSecret(apiKey);
        if (apiSecret == null) {
            // invalid digest
            log.error("Invalid API key");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid authorization data");
            return null;
        }

        CachingRequestWrapper requestWrapper = new CachingRequestWrapper(request);
        final byte[] contentAsByteArray = requestWrapper.getContentAsByteArray();


        final HmacSignatureBuilder signatureBuilder = HmacSignatureBuilder.buulder()
//                .scheme(request.getScheme())
                .method(request.getMethod())
                .uri(request.getRequestURI())
                .queryString(request.getQueryString())
                .contentType(request.getContentType())
                .apiSecret(apiSecret)
                .payload(contentAsByteArray);

        if (!signatureBuilder.isHashEquals(authHeader.getSignature())) {
            // invalid digest
            log.error("Invalid digest, should be: {}", signatureBuilder.buildAsBase64String());
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid authorization data");
            return null;
        }
        return requestWrapper;
    }
}

