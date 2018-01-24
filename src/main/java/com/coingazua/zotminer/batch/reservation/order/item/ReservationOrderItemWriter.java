package com.coingazua.zotminer.batch.reservation.order.item;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.coingazua.zotminer.business.transaction.repository.TransactionsHistoryRepository;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;

public class ReservationOrderItemWriter implements ItemWriter<TransactionsHistory> {
    @Autowired
    private TransactionsHistoryRepository transactionsHistoryRepository;

    @Override
    public void write(List<? extends TransactionsHistory> transactionsHistories) throws Exception {
        transactionsHistoryRepository.save(transactionsHistories);
    }
}

