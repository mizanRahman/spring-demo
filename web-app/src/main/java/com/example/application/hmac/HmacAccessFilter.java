package com.example.application.hmac;

import com.example.ep.filter.CachingRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Restricts access to resource if HMAC signature is not valid.
 * <p>
 * This filter does not provide Spring SecurityContext down to filter chain.
 */
@Slf4j
public class HmacAccessFilter extends OncePerRequestFilter {

    @Autowired
    private CredentialsProvider credentialsProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        log.info("inside hmac access filter");
        final AuthHeader authHeader = HmacUtil.getAuthHeader(request);


        if (authHeader == null) {
            // invalid authorization token
            logger.warn("Authorization header is missing");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        final String apiKey = authHeader.getApiKey();

        final byte[] apiSecret = credentialsProvider.getApiSecret(apiKey);
        if (apiSecret == null) {
            // invalid digest
            logger.error("Invalid API key");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid authorization data");
            return;
        }

        CachingRequestWrapper requestWrapper = new CachingRequestWrapper(request);
        final byte[] contentAsByteArray = requestWrapper.getContentAsByteArray();

        final HmacSignatureBuilder signatureBuilder = new HmacSignatureBuilder()
                .algorithm(authHeader.getScheme())
                .scheme(request.getScheme())
                .host(request.getServerName() + ":" + request.getServerPort())
                .method(request.getMethod())
                .resource(request.getRequestURI())
                .contentType(request.getContentType())
                .date(request.getHeader(HttpHeaders.DATE))
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .payload(contentAsByteArray);

        if (!signatureBuilder.isHashEquals(authHeader.getSignature())) {
            // invalid digest
            logger.error("Invalid digest");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid authorization data");
            return;
        }

        filterChain.doFilter(requestWrapper, response);
    }
}

