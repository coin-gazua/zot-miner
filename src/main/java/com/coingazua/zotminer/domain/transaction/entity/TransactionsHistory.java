package com.coingazua.zotminer.domain.transaction.entity;

import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.exchange.entity.Exchange;
import com.coingazua.zotminer.domain.order.model.OrderType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(catalog = "zotMiner", name = "transactions_history")
@Getter
@Setter
@ToString
public class TransactionsHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false)
    private Long seq;

    @Column(name = "exchange_seq", nullable = false)
    private Long exchangeSeq;

    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency = Currency.XRP;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;

    @Column(name = "order_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderType orderType = OrderType.ASK;

    @Column(name = "units_traded", nullable = false)
    private Double unitsTraded;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "total", nullable = false)
    private Double total;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_dt", nullable = false)
    private Date createDt;

    @ManyToOne
    @JoinColumn(name = "exchange_seq", insertable = false, updatable = false)
    private Exchange exchange;
}
