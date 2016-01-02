package com.example.unit;

import com.example.core.domain.errorcode.Components;
import com.example.core.domain.errorcode.ErrorCodes;
import com.example.core.domain.errorcode.ReasonCodes;
import com.example.core.domain.errorcode.Subjects;
import com.example.ep.model.ApiError;
import org.junit.Test;

/**
 * Created by mac on 1/2/16.
 */
public class Tests {
    @Test
    public void test() {
        System.out.println("errorCode=" + Components.CIS);


        String errorCode =
                ErrorCodes.getCode(Components.CIS, Subjects.MAP.Add_Card, ReasonCodes.DUPLICATE);
        ApiError etest = ApiError.builder()
                .errorCode(errorCode)
                .message(ErrorCodes.getMessage(errorCode))
                .build();

        System.out.println("test result for errorCode: "+ etest);


    }
}
