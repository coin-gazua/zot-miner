package com.coingazua.zotminer.business.user.repository;

import com.coingazua.zotminer.common.util.DateUtil;
import com.coingazua.zotminer.domain.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testInsert() {
        User user = new User();
        user.setUserId("test");
        user.setCreateDt(DateUtil.getTodayDateTime());
        userRepository.save(user);
    }

    @Test
    @Transactional
    public void testOneToMany() {
        User result = userRepository.findOne(1L);
        result.toString();
        assertTrue(result.getReservationOrderList().size() > 0);
        assertTrue(result.getUserExchangeList().size() > 0);
    }
}
