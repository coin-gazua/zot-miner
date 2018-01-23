package com.coingazua.zotminer.business.reservation.repository;

import com.coingazua.zotminer.common.util.DateUtil;
import com.coingazua.zotminer.domain.reservation.entity.ReservationOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationOrderRepositoryTests {

    @Autowired
    private ReservationOrderRepository reservationOrderRepository;

    @Test
    @Transactional
    public void testInsert() {
        ReservationOrder reservationOrder = new ReservationOrder();
        reservationOrder.setUserSeq(1L);
        reservationOrder.setExchangeSeq(1L);
        reservationOrder.setOrderValue(2000L);
        reservationOrder.setCreateDt(DateUtil.getTodayDateTime());
        reservationOrderRepository.save(reservationOrder);
    }

    @Test
    @Transactional
    public void testManyToOne() {
        ReservationOrder result = reservationOrderRepository.findOne(1L);
        assertNotNull(result.getUser().getUserId());
        assertNotNull(result.getExchange().getExchangeName());
    }
}
