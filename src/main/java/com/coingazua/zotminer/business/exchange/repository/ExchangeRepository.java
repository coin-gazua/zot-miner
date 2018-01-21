package com.coingazua.zotminer.business.exchange.repository;

import com.coingazua.zotminer.domain.exchange.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coingazua.zotminer.domain.user.entity.User;


public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

}
