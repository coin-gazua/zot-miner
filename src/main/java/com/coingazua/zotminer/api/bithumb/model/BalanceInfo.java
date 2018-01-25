package com.coingazua.zotminer.api.bithumb.model;

import com.coingazua.zotminer.common.util.DateUtil;
import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.order.model.OrderType;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
