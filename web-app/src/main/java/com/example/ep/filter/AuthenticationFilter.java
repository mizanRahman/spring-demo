package com.example.ep.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

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
        Enumeration<String> headerNames = httpRequest.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String s = headerNames.nextElement();
                log.info("Header >> {} : {}", s, httpRequest.getHeader(s));
            }
        }

        //doFilter
        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {
        log.info("destroyed");
    }
}

