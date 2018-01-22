package com.coingazua.zotminer.batch.transaction.recent.item;

import com.coingazua.zotminer.business.transaction.repository.TransactionsHistoryRepository;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RecentTransactionItemWriter implements ItemWriter<TransactionsHistory> {
    @Autowired
    private TransactionsHistoryRepository transactionsHistoryRepository;

    @Override
    public void write(List<? extends TransactionsHistory> transactionsHistories) throws Exception {
        transactionsHistoryRepository.save(transactionsHistories);
    }
}

