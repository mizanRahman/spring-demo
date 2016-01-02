package com.example.core.domain.errorcode;

import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;

/**
 * Created by mac on 1/2/16.
 */
public class ErrorCodes {

    public static HashMap<String, String> errorMap = new HashMap<>();
    private static final String CODE_SEPERATOR = ".";

    static {
        errorMap.put(
                getCode(Components.CP, Subjects.MAP.Add_Card,ReasonCodes.DUPLICATE),
                "duplicate entry while Adding Card on CP"
        );
    }

    public static String getCode(String component, String subject, String reason) {

        return new StringBuilder()
                .append(component)
                .append(CODE_SEPERATOR)
                .append(subject)
                .append(CODE_SEPERATOR)
                .append(reason)
                .toString();
    }

    public static String getMessage(String errorCode) {
        return errorMap.get(errorCode);
    }
}
