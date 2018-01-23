package com.coingazua.zotminer.batch.reservation.order.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;

public class ReservationOrderItemProcessor implements ItemProcessor<TransactionsHistory, TransactionsHistory> {

    private static final Logger log = LoggerFactory.getLogger(ReservationOrderItemProcessor.class);

    @Override
    public TransactionsHistory process(final TransactionsHistory transactionsHistory) throws Exception {
        final Long test = transactionsHistory.getPrice() + 1;
        transactionsHistory.setPrice(test);
        log.info(transactionsHistory.toString());
        return transactionsHistory;
    }
}
