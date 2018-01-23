package com.coingazua.zotminer.business.order.repository;

import com.coingazua.zotminer.common.util.DateUtil;
import com.coingazua.zotminer.domain.order.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTests {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Transactional
    public void testInsert() {
        Order order = new Order();
        order.setUserSeq(1L);
        order.setReservationOrderSeq(1L);
        order.setOrderId("111111111");
        order.setCreateDt(DateUtil.getTodayDateTime());
        orderRepository.save(order);
    }

    @Test
    @Transactional
    public void testOneToMany() {
        Order result = orderRepository.findOne(2L);
        assertNotNull(result.getReservationOrder().getOrderValue());
        assertNotNull(result.getUser().getUserId());
    }
}
