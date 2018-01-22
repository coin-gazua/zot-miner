package com.coingazua.zotminer.api.bithumb.model;

import com.coingazua.zotminer.domain.order.model.OrderType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

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

    public void setType(String type) {
        if (StringUtils.isNotEmpty(type)) {
            this.type = OrderType.valueOf(type.toUpperCase());
        }
    }
}
