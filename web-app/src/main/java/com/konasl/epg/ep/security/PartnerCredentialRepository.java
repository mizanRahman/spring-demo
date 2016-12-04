package com.konasl.epg.ep.security;

/**
 * Created by mac on 12/3/16.
 */
public interface PartnerCredentialRepository {
    PartnerCredential getCredential(String ApiKey);
}
