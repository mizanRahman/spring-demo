package com.example.ep.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;

/**
 * Created by mac on 11/26/16.
 */
@Slf4j
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (isAuthenticated(httpRequest)) {
            chain.doFilter(httpRequest, response);
        }
    }

    @Override
    public void destroy() {
        log.info("destroyed");
    }


    public boolean isAuthenticated(HttpServletRequest httpRequest) {
        String NL = System.lineSeparator();
        String stringToSign;
        try {
            stringToSign = new StringBuilder("KONA-HMAC-SHA266")
                    .append(NL)
                    .append(httpRequest.getMethod())
                    .append(NL)
                    .append(httpRequest.getRequestURI())
                    .append(NL)
                    .append(httpRequest.getContentType() == null ? "" : httpRequest.getContentType())
                    .append(NL)
                    .append(
                            new URI(
                                    httpRequest.getQueryString() != null ? httpRequest.getQueryString() : "")
                                    .normalize())
                    .append(NL)
                    .append(
                            DatatypeConverter.printHexBinary(
                                    MessageDigest.getInstance("SHA-256")
                                            .digest(
                                                    IOUtils.toByteArray(
                                                            new RequestWrapper(httpRequest).getInputStream()))))
                    .toString();

            log.info("String to Sign = {} ", stringToSign);

            String signature = calculateSignature("12345", stringToSign);
            log.info("hmac={}", signature);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public static String calculateSignature(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        byte[] hmac = sha256_HMAC.doFinal(data.getBytes("UTF-8"));

        return DatatypeConverter.printBase64Binary(hmac);
    }
}

