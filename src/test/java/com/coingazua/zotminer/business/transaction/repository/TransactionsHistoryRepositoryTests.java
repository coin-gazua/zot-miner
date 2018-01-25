package com.coingazua.zotminer.business.transaction.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.coingazua.zotminer.common.util.DateUtil;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
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
    
    @Test
    @Transactional
    public void testPastDelete() {
    	Date deleteDate = Date.from(LocalDateTime.now().minusDays(3).atZone(ZoneId.systemDefault()).toInstant());
    	log.info("삭제일자 : {}", deleteDate.toString());
        List<TransactionsHistory> deletedObject = transactionsHistoryRepository.deleteByCreateDtLessThan(deleteDate);
        long deleteCount = deletedObject.stream()
        	.filter( o -> o.getCreateDt().after(deleteDate) )
        	.count();        
        assertTrue(deleteCount == 0);
    }
}
