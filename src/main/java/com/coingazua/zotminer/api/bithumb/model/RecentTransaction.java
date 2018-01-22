package com.coingazua.zotminer.api.bithumb.model;

import com.coingazua.zotminer.domain.order.model.OrderType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Getter
@Setter
public class RecentTransaction {
    private Date transactionDate;
    private OrderType type;
    private Double unitsTraded;
    private Long price;
    private Double total;

    public void setType(String type) {
        if (StringUtils.isNotEmpty(type)) {
            this.type = OrderType.valueOf(type.toUpperCase());
        }
    }
}
