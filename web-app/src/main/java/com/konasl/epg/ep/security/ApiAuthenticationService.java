package com.konasl.epg.ep.security;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mac on 11/30/16.
 */
public interface ApiAuthenticationService {
    boolean isAuthenticated(HttpServletRequest httpRequest);
}
