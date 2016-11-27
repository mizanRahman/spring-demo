package com.example.ep.controller;

import com.example.ep.model.CouponInfo;
import com.example.ep.model.CouponSearchRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mac on 11/28/15.
 */
@RestController
@RequestMapping("/m12")
@Slf4j
public class M12PaymentEndPoint {

    @RequestMapping(path = "/coupons/{couponNo}")
    public CouponInfo pay(@PathVariable Long couponNo, @RequestBody CouponSearchRequest couponSearchRequest) {

        return CouponInfo.builder()
                .tokenNumber("123456")
                .balance(122L)
                .build();
    }

}
