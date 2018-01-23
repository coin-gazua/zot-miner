package com.coingazua.zotminer.domain.exchange.model;

public enum ExchangeName {
    BITHUMB("빗썸"),
    UPBIT("업비트"),
    OKCOIN("오케이코인");

    private final String value;

    ExchangeName(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
