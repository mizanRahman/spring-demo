package com.example.ep.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Created by mac on 11/29/15.
 */
@Builder
@Data
@ToString
public class ApiError {
    private String status;
    private String errorCode;
    private String message;
}
