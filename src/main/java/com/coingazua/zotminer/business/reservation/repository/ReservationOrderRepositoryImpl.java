package com.coingazua.zotminer.business.reservation.repository;

import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.reservation.entity.QReservationOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReservationOrderRepositoryImpl implements ReservationOrderRepositoryCustom {
	@Autowired
	private JPAQueryFactory queryFactory;

	private QReservationOrder reservationOrder = QReservationOrder.reservationOrder;

	@Override
	public List<Currency> getGroupBy(boolean isUse) {
		return queryFactory.select(reservationOrder.currency)
		        .from(reservationOrder)
		        .groupBy(reservationOrder.currency)
		        .fetch();
	}
}
