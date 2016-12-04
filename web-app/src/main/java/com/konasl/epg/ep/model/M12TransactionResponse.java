package com.konasl.epg.ep.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by mac on 11/27/16.
 */
@Data
@Builder
public class M12TransactionResponse {
    private String barcodeNo;
    private String tokenNumber;
    private String komServiceId;
    private Long expDate;
    private Long balance;
    private String approaveId;
    private String resCode;
}
