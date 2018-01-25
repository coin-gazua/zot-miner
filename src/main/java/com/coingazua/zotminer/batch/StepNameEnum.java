package com.coingazua.zotminer.batch;

/**
 * Created by uienw00 on 2018. 1. 19..
 */
public enum StepNameEnum {
    RECENT_TRANSACTION_1("최근 거래 내역 스텝1"),
    RESERVATION_ORDER_1("예약 주문 스텝1"),
    DELETE_TRANSACTION_1("거래 내역 삭제 스텝1");

    private final String value;

    StepNameEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
