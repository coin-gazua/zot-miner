package com.coingazua.zotminer.domain.order.model;

/**
 * Created by uienw00 on 2018. 1. 19..
 */
public enum OrderType {
     ASK("판매")
    ,BID("구매");

    private final String value;
    OrderType(final String value){this.value = value;}

    public String getValue() {
        return value;
    }
}
