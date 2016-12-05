package com.konasl.epg.domain.service;

import com.konasl.epg.domain.model.authorization.AuthorizationMessage;

/**
 * Created by mac on 12/3/16.
 */
public interface TransactionAuthorizationService {

    AuthorizationMessage authorize(AuthorizationMessage message);
}
