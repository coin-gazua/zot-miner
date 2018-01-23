package com.coingazua.zotminer.business.reservation.repository;

import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.reservation.entity.QReservationOrder;
import com.coingazua.zotminer.domain.reservation.entity.ReservationOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
