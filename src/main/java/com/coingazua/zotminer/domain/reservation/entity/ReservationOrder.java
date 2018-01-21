package com.coingazua.zotminer.domain.reservation.entity;

import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.exchange.entity.Exchange;
import com.coingazua.zotminer.domain.order.model.OrderType;
import com.coingazua.zotminer.domain.order.model.OrderValueType;
import com.coingazua.zotminer.domain.reservation.model.ReservationType;
import com.coingazua.zotminer.domain.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation_order")
@Getter
@Setter
@ToString
public class ReservationOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false)
    private Long seq;

    @Column(name = "user_seq", nullable = false)
    private Long userSeq;

    @Column(name = "exchange_seq", nullable = false)
    private Long exchangeSeq;

    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency = Currency.XRP;

    @Column(name = "order_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderType orderType = OrderType.BID;

    @Column(name = "reservation_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationType reservationType = ReservationType.FIX;

    @Column(name = "order_value", nullable = false)
    private Long orderValue;

    @Column(name = "order_value_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderValueType orderValueType = OrderValueType.PRICE;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_dt", nullable = false)
    private Date createDt;

    @ManyToOne
    @JoinColumn(name = "user_seq", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "exchange_seq", insertable = false, updatable = false)
    private Exchange exchange;
}
