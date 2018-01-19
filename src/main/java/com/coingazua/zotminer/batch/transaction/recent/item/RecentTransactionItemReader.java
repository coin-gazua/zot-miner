package com.coingazua.zotminer.batch.transaction.recent.item;

import com.coingazua.zotminer.business.transaction.repository.TransactionsHistoryRepository;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@StepScope
public class RecentTransactionItemReader implements ItemReader<TransactionsHistory> {

    @Autowired
    private TransactionsHistoryRepository transactionsHistoryRepository;

    private List<TransactionsHistory> transactionsHistories;
    private int fetchCount;

    @BeforeStep
    void beforeStep(final StepExecution stepExecution) {
        System.out.println("=========================================beforeStep==========================");
        fetchCount = 0; //스케쥴로 계속 실행 되기 때문에 초기화 해줌
        JobParameters parameters = stepExecution.getJobExecution().getJobParameters();
        System.out.println(parameters.getString("parameter"));
        transactionsHistories = transactionsHistoryRepository.findAll();
    }

    @Override
    public TransactionsHistory read() {
        System.out.println("=========================================read==========================");
        if(fetchCount < this.transactionsHistories.size()){
            return this.transactionsHistories.get(fetchCount++);
        }else{
            return null;
        }
    }


}

