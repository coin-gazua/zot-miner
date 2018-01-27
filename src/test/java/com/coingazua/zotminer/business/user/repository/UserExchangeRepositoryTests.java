package com.coingazua.zotminer.business.user.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.coingazua.zotminer.common.util.DateUtil;
import com.coingazua.zotminer.domain.user.entity.UserExchange;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserExchangeRepositoryTests {

	@Autowired
	private UserExchangeRepository userExchangeRepository;

	@Test
	@Transactional
	public void testInsert() {
		UserExchange userExchange = new UserExchange();
		userExchange.setUserSeq(100L);
		userExchange.setExchangeSeq(1L);
		userExchange.setAccountId("1111");
		userExchange.setApiKey("22222");
		userExchange.setSecretKey("33333");
		userExchange.setCreateDt(DateUtil.getTodayDateTime());
		userExchangeRepository.save(userExchange);
	}

	@Test
	public void testGetUserExchange() {
		List<UserExchange> result = userExchangeRepository.getUserExchange(true);
		assertTrue(result.size() > 0);
	}
}
