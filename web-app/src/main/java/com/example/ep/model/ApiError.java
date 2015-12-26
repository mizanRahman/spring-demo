package com.example.ep.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by mac on 11/29/15.
 */
@Builder
@Data
public class ApiError {
    private String status;
    private String errorCode;
    private String message;
}
