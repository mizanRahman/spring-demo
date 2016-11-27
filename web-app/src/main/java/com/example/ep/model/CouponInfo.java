package com.example.ep.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by mac on 11/27/16.
 */
@Data
@Builder
public class CouponInfo {
    private String barcodeNo;
    private String tokenNumber;
    private String komServiceId;
    private Long expDate;
    private Long balance;
    private String approaveId;
    private String resCode;
}
