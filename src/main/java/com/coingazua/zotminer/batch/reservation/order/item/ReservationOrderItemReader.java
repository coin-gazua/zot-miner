package com.coingazua.zotminer.batch.reservation.order.item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.coingazua.zotminer.business.user.repository.UserRepository;
import com.coingazua.zotminer.business.user.service.UserExchangeService;
import com.coingazua.zotminer.domain.user.entity.User;
import com.coingazua.zotminer.domain.user.entity.UserExchange;
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
    @Autowired
    private UserExchangeService userExchangeService;

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private BithumbApi bithumbApi;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationOrderRepository reservationOrderRepository;

    private List<TransactionsHistory> transactionsHistories;
    private int fetchCount;

    @BeforeStep
    void beforeStep(final StepExecution stepExecution) {
        //스케쥴로 계속 실행 되기 때문에 초기화 해줌
        fetchCount = 0;
        transactionsHistories = new ArrayList<>();

        List<User> users = userRepository.findByIsUse(true);

        users.stream().forEach(user -> {
            List<UserExchange> exchanges = userExchangeService.getUserExchange(user.getSeq());
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

