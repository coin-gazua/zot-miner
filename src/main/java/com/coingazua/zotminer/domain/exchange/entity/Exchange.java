package com.coingazua.zotminer.domain.exchange.entity;

import com.coingazua.zotminer.domain.exchange.model.ExchangeName;
import com.coingazua.zotminer.domain.order.entity.Order;
import com.coingazua.zotminer.domain.user.entity.UserExchange;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(catalog = "zotMiner", name = "exchange")
@Getter
@Setter
@ToString(exclude = {"userExchangeList"})
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false)
    private Long seq;

    @Column(name = "exchange_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExchangeName exchangeName = ExchangeName.BITHUMB;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_dt", nullable = false)
    private Date createDt;

    @OneToMany(mappedBy = "exchange", fetch = FetchType.LAZY)
    private List<UserExchange> userExchangeList = new ArrayList<UserExchange>();
}
