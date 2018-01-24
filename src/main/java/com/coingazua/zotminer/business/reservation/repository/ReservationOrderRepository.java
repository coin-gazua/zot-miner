package com.coingazua.zotminer.business.reservation.repository;

import com.coingazua.zotminer.domain.reservation.entity.ReservationOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReservationOrderRepository extends JpaRepository<ReservationOrder, Long>, ReservationOrderRepositoryCustom {
}
