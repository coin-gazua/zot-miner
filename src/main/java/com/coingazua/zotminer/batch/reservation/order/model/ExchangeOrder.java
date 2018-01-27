package com.coingazua.zotminer.batch.reservation.order.model;

import com.coingazua.zotminer.domain.reservation.entity.ReservationOrder;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ExchangeOrder {
    private String apiKey;
    private String secretKey;
    private ReservationOrder reservationOrder;

    public ExchangeOrder(String apiKey, String secretKey, ReservationOrder reservationOrder){
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.reservationOrder = reservationOrder;
    }
}
