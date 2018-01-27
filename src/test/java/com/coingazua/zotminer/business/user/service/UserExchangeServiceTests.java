package com.coingazua.zotminer.business.user.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.coingazua.zotminer.domain.user.entity.UserExchange;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserExchangeServiceTests {

	@Autowired
	private UserExchangeService userExchangeService;

	@Test
	public void testGetUserExchange() {
		//userSeq 별로 쿼리 한번만 나가는지 캐쉬 테스트
		List<UserExchange> result = userExchangeService.getCacheableUserExchange(true);
		System.out.println(result.toString());
		result = userExchangeService.getCacheableUserExchange(true);
		System.out.println(result.toString());
		assertTrue(result.size() > 0);
	}
}
