package com.coingazua.zotminer.batch.reservation.order.item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.coingazua.zotminer.api.bithumb.client.BithumbApi;
import com.coingazua.zotminer.business.exchange.service.ExchangeService;
import com.coingazua.zotminer.business.reservation.repository.ReservationOrderRepository;
import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.exchange.entity.Exchange;
import com.coingazua.zotminer.domain.reservation.entity.ReservationOrder;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;

@StepScope
public class ReservationOrderItemReader implements ItemReader<TransactionsHistory> {
    private final static String EXCHANGE_KEY = "zotminer:exchange";

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private ReservationOrderRepository reservationOrderRepository;

    @Autowired
    private BithumbApi bithumbApi;

    private List<TransactionsHistory> transactionsHistories;
    private int fetchCount;

    @BeforeStep
    void beforeStep(final StepExecution stepExecution) {
        System.out.println("=========================================beforeStep==========================");

        //스케쥴로 계속 실행 되기 때문에 초기화 해줌
        fetchCount = 0;
        transactionsHistories = new ArrayList<>();

        List<Exchange> exchanges = exchangeService.getAllExchange();
        exchanges.forEach(exchange -> {
            switch (exchange.getExchangeName()) {
                case BITHUMB:
                    //예약 주문에 해당하는 코인 정보만 조회
                    List<Currency> currencies = reservationOrderRepository.getGroupBy(true);
                    currencies.forEach(currency -> {
                        transactionsHistories.addAll(bithumbApi.recentTransaction(exchange.getSeq(), currency));
                    });
            }
        });
    }

    @Override
    public TransactionsHistory read() {
        System.out.println("=========================================read==========================");
        if (fetchCount < this.transactionsHistories.size()) {
            return this.transactionsHistories.get(fetchCount++);
        } else {
            return null;
        }
    }


}

