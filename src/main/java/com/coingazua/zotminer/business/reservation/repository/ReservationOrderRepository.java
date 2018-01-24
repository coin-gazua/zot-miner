package com.coingazua.zotminer.business.reservation.repository;

import com.coingazua.zotminer.domain.reservation.entity.ReservationOrder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationOrderRepository extends JpaRepository<ReservationOrder, Long>, ReservationOrderRepositoryCustom {
    ReservationOrder findFirstByUserSeqAndExchangeSeqAndIsOrderAndIsUseOrderBySeqDesc(Long userSeq, Long exchangeSeq, boolean isOrder, boolean isUse);
}
