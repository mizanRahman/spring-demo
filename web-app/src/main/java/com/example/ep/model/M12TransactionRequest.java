package com.example.ep.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by mac on 11/28/16.
 */
@Data
@Builder
public class M12TransactionRequest {
    private String barcodeNo;
    private String marketCode;
    private String posCode;
    private String trId;
    private Long transactionDateTime;
    private Long amount;
}
