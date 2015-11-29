package com.example;

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
