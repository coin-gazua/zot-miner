package com.coingazua.zotminer.api.bithumb.model;

import com.coingazua.zotminer.common.util.DateUtil;
import com.coingazua.zotminer.domain.order.model.OrderType;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@ToString
public class RecentTransaction {
    private String transaction_date;
    private OrderType type;
    private Double units_traded;
    private Long price;
    private Double total;
    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void setType(String type) {
        if (StringUtils.isNotEmpty(type)) {
            this.type = OrderType.valueOf(type.toUpperCase());
        }
    }

    public TransactionsHistory convertTransactionsHistory(long exchangeSeq) {
        TransactionsHistory transactionsHistory = new TransactionsHistory();
        try {
            Date date = simpleDateFormat.parse(transaction_date);
            transactionsHistory.setTransactionDate(date);
        } catch (ParseException e) {
        }

        transactionsHistory.setExchangeSeq(exchangeSeq);
        transactionsHistory.setOrderType(this.type);
        transactionsHistory.setUnitsTraded(this.units_traded);
        transactionsHistory.setPrice(this.price);
        transactionsHistory.setTotal(this.total);
        transactionsHistory.setCreateDt(DateUtil.getTodayDateTime());
        return transactionsHistory;
    }
}
