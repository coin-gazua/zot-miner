package com.coingazua.zotminer.business.exchange.repository;

import com.coingazua.zotminer.domain.exchange.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

}
