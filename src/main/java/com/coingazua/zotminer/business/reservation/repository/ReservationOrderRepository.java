package com.coingazua.zotminer.business.reservation.repository;

import com.coingazua.zotminer.domain.reservation.entity.ReservationOrder;
import com.coingazua.zotminer.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationOrderRepository extends JpaRepository<ReservationOrder, Long> {

}
