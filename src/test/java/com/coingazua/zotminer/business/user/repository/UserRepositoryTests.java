package com.coingazua.zotminer.business.user.repository;

import com.coingazua.zotminer.common.util.DateUtil;
import com.coingazua.zotminer.domain.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	@Transactional
	public void testInsert(){
		User user = new User();
		user.setUserId("dssp");
		user.setAccountId("11111");
		user.setApiKey("222222");
		user.setSecretKey("3333333");
		user.setCreateDt(DateUtil.getTodayDateTime());
		userRepository.save(user);
	}
}
