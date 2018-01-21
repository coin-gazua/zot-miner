package com.coingazua.zotminer.domain.user.entity;

import com.coingazua.zotminer.domain.reservation.entity.ReservationOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString(exclude = { "reservationOrderList", "userExchangeList" })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false)
    private Long seq;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_dt", nullable = false)
    private Date createDt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ReservationOrder> reservationOrderList = new ArrayList<ReservationOrder>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserExchange> userExchangeList = new ArrayList<UserExchange>();
}
