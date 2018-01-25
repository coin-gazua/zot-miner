package com.coingazua.zotminer.batch;

/**
 * Created by uienw00 on 2018. 1. 19..
 */
public enum JobNameEnum {
    RECENT_TRANSACTION("최근 거래 내역"),
    RESERVATION_ORDER("예약주문"),
    DELETE_TRANSACTION("거래 내역 삭제");

    private final String value;

    JobNameEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
