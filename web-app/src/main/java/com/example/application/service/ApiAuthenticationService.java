package com.example.application.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mac on 11/30/16.
 */
public interface ApiAuthenticationService {
    boolean isAuthenticated(HttpServletRequest httpRequest);
}
