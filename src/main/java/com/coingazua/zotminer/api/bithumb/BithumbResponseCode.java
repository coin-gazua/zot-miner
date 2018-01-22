package com.coingazua.zotminer.api.bithumb;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by uienw00 on 2018. 1. 22..
 */
public enum BithumbResponseCode {
     SUCCESS("0000")
    ,BAD_REQUEST("5100")
    ,NOT_MEMBER("5200")
    ,INVALID_APIKEY("5300")
    ,METHOD_NOT_ALLOWED("5302")
    ,DATABASE_FAIL("5400")
    ,INVALID_PARAMETER("5500")
    ,CUSTOM_NOTICE("5600")
    ,UNKNOWN_ERROR("5900");

    private final String value;

    BithumbResponseCode(final String value) {
        this.value = value;
    }

    private final static Map<String, BithumbResponseCode> codeMap = Arrays.stream(BithumbResponseCode.values())
            .collect(Collectors.toMap(codeEnum -> codeEnum.value, codeEnum -> codeEnum));

    public static BithumbResponseCode get(String value) {
        return codeMap.get(value);
    }

    public String getValue() {
        return value;
    }
}
