package com.coingazua.zotminer.api.bithumb.client;

import com.coingazua.zotminer.api.bithumb.model.BalanceInfo;
import com.coingazua.zotminer.batch.reservation.order.model.ExchangeOrder;
import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.reservation.entity.ReservationOrder;
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

    @Test(expected = NullPointerException.class)
    public void testBalanceInfo(){
        String apiKey = "1111";
        String secretKey = "2222";
        ReservationOrder reservationOrder = new ReservationOrder();
        reservationOrder.setCurrency(Currency.BTC);
        ExchangeOrder exchangeOrder = new ExchangeOrder(apiKey, secretKey, reservationOrder);
        BalanceInfo result = bithumbApi.balanceInfo(exchangeOrder);
        System.out.println(result.getInUseCurrency());
        System.out.println(result.toString());
        //assertNotNull(result);
    }
}
