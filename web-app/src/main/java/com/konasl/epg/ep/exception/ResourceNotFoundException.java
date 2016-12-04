package com.konasl.epg.ep.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * Created by mac on 12/30/15.
 */
public class ResourceNotFoundException extends NestedRuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
