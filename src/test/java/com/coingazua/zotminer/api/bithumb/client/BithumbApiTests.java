package com.coingazua.zotminer.api.bithumb.client;

import com.coingazua.zotminer.api.bithumb.model.BalanceInfo;
import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;
import com.coingazua.zotminer.domain.user.entity.UserExchange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
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

    @Test
    public void testBalanceInfo(){
        UserExchange userExchange = new UserExchange();
        userExchange.setApiKey("9c65f1c7382779d016d3e70bf8b0ac4d");
        userExchange.setSecretKey("6875876a29000e57c667ee460dcb60db");
       /* userExchange.setApiKey("d3528f73f00ff12a1c8fc19f73409934");
        userExchange.setSecretKey("6a203d99fb52ab7aefb3c518af643ad6");*/
        BalanceInfo result = bithumbApi.balanceInfo(userExchange, Currency.XRP);
        System.out.println(result.getInUseCurrency());
        System.out.println(result.toString());
        assertNotNull(result);
    }
}
