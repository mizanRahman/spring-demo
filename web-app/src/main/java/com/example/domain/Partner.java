package com.example.domain;

import com.example.domain.core.PartnerId;
import lombok.Data;

/**
 * Created by mac on 12/3/16.
 */
@Data
public class Partner {
    private PartnerId partnerId;
    private String serviceGroupId;
}
