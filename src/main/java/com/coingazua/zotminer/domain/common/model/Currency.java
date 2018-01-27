package com.coingazua.zotminer.domain.common.model;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by uienw00 on 2018. 1. 19..
 */
public enum Currency {
    BTC("비트코인"),
    ETH("이더리움"),
    DASH("대쉬"),
    LTC("라이트코인"),
    XRP("리플"),
    BCH("비트코인캐쉬"),
    XMR("모네로"),
    ZEC("제트캐쉬"),
    QTUM("퀀텀"),
    BTG("비트코인골드"),
    EOS("이오스");

    private final String value;

    Currency(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
