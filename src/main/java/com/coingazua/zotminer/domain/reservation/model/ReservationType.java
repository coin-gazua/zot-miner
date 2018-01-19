package com.coingazua.zotminer.domain.reservation.model;

/**
 * Created by uienw00 on 2018. 1. 19..
 */
public enum ReservationType {
     FIX("고정")
    ,VAR("변동");

    private final String value;
    ReservationType(final String value){this.value = value;}

    public String getValue() {
        return value;
    }
}
