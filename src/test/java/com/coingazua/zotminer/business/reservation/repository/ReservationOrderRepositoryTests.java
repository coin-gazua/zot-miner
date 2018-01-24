package com.coingazua.zotminer.business.reservation.repository;

import com.coingazua.zotminer.common.util.DateUtil;
import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.reservation.entity.ReservationOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
    public void testFindAll() {
        List<ReservationOrder> result = reservationOrderRepository.findAll();
        assertTrue(result.size() > 0);
    }

    @Test
    public void testManyToOne() {
        ReservationOrder result = reservationOrderRepository.findOne(1L);
        assertNotNull(result.getUser().getUserId());
        assertNotNull(result.getExchange().getExchangeName());
    }

    @Test
    public void testGetGroupBy(){
        List<Currency> result = reservationOrderRepository.getGroupBy(true);
        assertTrue(result.size() > 0);
    }

    @Test
    public void testFindFirstByUserSeqAndExchangeSeqAndIsOrderAndIsUseOrderBySeqDesc(){
        ReservationOrder result =reservationOrderRepository.findFirstByUserSeqAndExchangeSeqAndIsOrderAndIsUseOrderBySeqDesc(1L, 1L, false, true);
        assertNotNull(result);
    }
}
