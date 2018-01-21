package com.coingazua.zotminer.business.exchange.repository;

import com.coingazua.zotminer.domain.exchange.entity.Exchange;
import com.coingazua.zotminer.domain.exchange.model.ExchangeName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.coingazua.zotminer.business.user.repository.UserRepository;
import com.coingazua.zotminer.common.util.DateUtil;
import com.coingazua.zotminer.domain.user.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangeRepositoryTests {

	@Autowired
	private ExchangeRepository exchangeRepository;

	@Test
	@Transactional
	public void testInsert(){
		Exchange exchange = new Exchange();
		exchange.setExchangeName(ExchangeName.BITHUMB);
		exchange.setCreateDt(DateUtil.getTodayDateTime());
		exchangeRepository.save(exchange);
	}
}
