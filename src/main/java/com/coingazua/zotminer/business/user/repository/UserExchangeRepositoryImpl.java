package com.coingazua.zotminer.business.user.repository;

import java.util.List;

import com.coingazua.zotminer.domain.user.entity.QUser;
import com.coingazua.zotminer.domain.user.entity.QUserExchange;
import com.coingazua.zotminer.domain.user.entity.UserExchange;
import org.springframework.beans.factory.annotation.Autowired;

import com.coingazua.zotminer.business.reservation.repository.ReservationOrderRepositoryCustom;
import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.reservation.entity.QReservationOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class UserExchangeRepositoryImpl implements UserExchangeRepositoryCustom {
	@Autowired
	private JPAQueryFactory queryFactory;

	private QUserExchange userExchange = QUserExchange.userExchange;
	private QUser user = QUser.user;

	@Override
	public List<UserExchange> getUserExchange(boolean isUse) {
		return queryFactory.select(userExchange)
		        .from(userExchange)
				.innerJoin(userExchange.user, user)
				.where(user.isUse.eq(isUse))
		        .fetch();
	}
}
