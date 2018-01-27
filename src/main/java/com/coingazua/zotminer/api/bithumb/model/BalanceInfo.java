package com.coingazua.zotminer.api.bithumb.model;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import com.coingazua.zotminer.domain.common.model.Currency;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BalanceInfo {
    private Double totalCurrency;
    private Double totalKrw;
    private Double inUseCurrency;
    private Double inUseKrw;
    private Double availableCurrency;
    private Double availableKrw;
    private Double xcoinLast;

    public BalanceInfo(Map<String, String> map, Currency currency){
        String currencyName = currency.name().toLowerCase();

        this.totalCurrency = MapUtils.getDouble(map, "total_" + currencyName);
        this.totalKrw = MapUtils.getDouble(map, "total_krw");
        this.inUseCurrency = MapUtils.getDouble(map, "in_use_" + currencyName);
        this.inUseKrw = MapUtils.getDouble(map, "in_use_krw");
        this.availableCurrency = MapUtils.getDouble(map, "available_" + currencyName);
        this.availableKrw = MapUtils.getDouble(map, "available_krw");
        this.xcoinLast = MapUtils.getDouble(map, "xcoin_last");
    }
}
