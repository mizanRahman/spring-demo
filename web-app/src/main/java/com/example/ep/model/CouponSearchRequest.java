package com.example.ep.model;

import lombok.Data;

/**
 * Created by mac on 11/28/16.
 */
@Data
public class CouponSearchRequest {
    private String barcodeNo;
    private String marketCode;
    private String posCode;
}
