package com.example.ep.exception;

import lombok.Data;

/**
 * Created by mac on 1/2/16.
 */
@Data
public class DomainException extends RuntimeException {

    private String featureCode;
    private String reasonCode;

    public DomainException(String reasonCode,String featureCode,
                           String message, Throwable cause) {

        super(message, cause);
        this.reasonCode = reasonCode;
    }

    public DomainException(String reasonCode, String featureCode, Throwable cause) {

        this(reasonCode, featureCode, cause.getMessage(), cause);
    }
}
