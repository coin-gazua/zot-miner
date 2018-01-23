package com.coingazua.zotminer.domain.order.entity;

import com.coingazua.zotminer.domain.reservation.entity.ReservationOrder;
import com.coingazua.zotminer.domain.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(catalog = "zotMiner", name = "order")
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false)
    private Long seq;

    @Column(name = "reservation_order_seq", nullable = false)
    private Long reservationOrderSeq;

    @Column(name = "user_seq", nullable = false)
    private Long userSeq;

    @Column(name = "order_id", nullable = false)
    private String orderId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_dt", nullable = false)
    private Date createDt;

    @ManyToOne
    @JoinColumn(name = "reservation_order_seq", insertable = false, updatable = false)
    private ReservationOrder reservationOrder;

    @ManyToOne
    @JoinColumn(name = "user_seq", insertable = false, updatable = false)
    private User user;

}
