package com.coingazua.zotminer.business.reservation.repository;

import com.coingazua.zotminer.domain.reservation.entity.ReservationOrder;
import com.coingazua.zotminer.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReservationOrderRepository extends JpaRepository<ReservationOrder, Long> {
    List<ReservationOrder> findByExchangeSeq(Long exchangeSeq);
}
