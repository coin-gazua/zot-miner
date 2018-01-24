package com.coingazua.zotminer.batch.transaction.recent.item;

import com.coingazua.zotminer.api.bithumb.client.BithumbApi;
import com.coingazua.zotminer.business.exchange.service.ExchangeService;
import com.coingazua.zotminer.business.reservation.repository.ReservationOrderRepository;
import com.coingazua.zotminer.business.transaction.repository.TransactionsHistoryRepository;
import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.exchange.entity.Exchange;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@StepScope
public class RecentTransactionTasklet implements Tasklet {
    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private BithumbApi bithumbApi;

    @Autowired
    private ReservationOrderRepository reservationOrderRepository;

    @Autowired
    private TransactionsHistoryRepository transactionsHistoryRepository;

    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        List<TransactionsHistory> transactionsHistories = new ArrayList<>();

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
        transactionsHistoryRepository.save(transactionsHistories);
        return RepeatStatus.FINISHED;
    }
}

