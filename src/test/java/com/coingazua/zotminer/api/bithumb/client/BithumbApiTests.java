package com.coingazua.zotminer.api.bithumb.client;

import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BithumbApiTests {

    @Autowired
    private BithumbApi bithumbApi;

    @Test
    public void testRecentTransaction() throws Exception {
        List<TransactionsHistory> result = bithumbApi.recentTransaction(1L, Currency.BTC);
        assertTrue(result.size() > 0);
    }
}
