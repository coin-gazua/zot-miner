package com.coingazua.zotminer.business.transaction.repository;

import com.coingazua.zotminer.common.util.DateUtil;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionsHistoryRepositoryTests {

    @Autowired
    private TransactionsHistoryRepository transactionsHistoryRepository;

    @Test
    @Transactional
    public void testInsert() {
        Date currentDate = DateUtil.getTodayDateTime();
        TransactionsHistory transactionsHistory = new TransactionsHistory();
        transactionsHistory.setExchangeSeq(1L);
        transactionsHistory.setPrice(1000L);
        transactionsHistory.setUnitsTraded(1.2);
        transactionsHistory.setTotal(transactionsHistory.getPrice() * transactionsHistory.getUnitsTraded());
        transactionsHistory.setTransactionDate(currentDate);
        transactionsHistory.setCreateDt(currentDate);
        transactionsHistoryRepository.save(transactionsHistory);
    }

    @Test
    public void testManyToOne() {
        TransactionsHistory result = transactionsHistoryRepository.findOne(5134L);
        assertNotNull(result);
    }

}
