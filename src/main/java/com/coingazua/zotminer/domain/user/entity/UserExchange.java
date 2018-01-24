package com.coingazua.zotminer.domain.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(catalog = "zotMiner", name = "user_exchange")
@Getter
@Setter
@ToString
public class UserExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false)
    private Long seq;

    @Column(name = "user_seq", nullable = false)
    private Long userSeq;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(name = "api_key", nullable = false)
    private String apiKey;

    @Column(name = "secret_key", nullable = false)
    private String secretKey;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_dt", nullable = false)
    private Date createDt;

    @ManyToOne
    @JoinColumn(name = "user_seq", insertable = false, updatable = false)
    private User user;
}
