package com.example.ep.filter;

import com.example.application.impl.HmacApiAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by mac on 11/26/16.
 */
@Slf4j
@Component("preControllerServiceHandle")
public class PreControllerServiceHandler implements Filter {


    @Autowired
    HmacApiAuthenticationService hmacApiAuthenticationService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        MultiReadHttpServeletRequestWrapper multiReadHttpServeletRequestWrapper = new MultiReadHttpServeletRequestWrapper(httpRequest);

        DateTime start = DateTime.now();
        if (hmacApiAuthenticationService.isAuthenticated(multiReadHttpServeletRequestWrapper)) {
            chain.doFilter(multiReadHttpServeletRequestWrapper, response);
        }
        DateTime end = DateTime.now();

        org.joda.time.Duration duration = new Interval(start, end).toDuration();
        log.info("duration = {}", duration.toString());

    }

    @Override
    public void destroy() {

    }

}

