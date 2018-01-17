package com.coingazua.zotminer.home.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "miner_test")
@Getter
@Setter
@ToString
public class MinerTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false)
    private Long seq;

    @Column(name = "test_str")
    private String test_str;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "test_date")
    private Date testDate;
}
