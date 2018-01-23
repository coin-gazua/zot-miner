package com.coingazua.zotminer.business.reservation.repository;

import java.util.List;

import com.coingazua.zotminer.domain.common.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coingazua.zotminer.domain.reservation.entity.ReservationOrder;


public interface ReservationOrderRepositoryCustom {
    List<Currency> getGroupBy(boolean isUse);
}
