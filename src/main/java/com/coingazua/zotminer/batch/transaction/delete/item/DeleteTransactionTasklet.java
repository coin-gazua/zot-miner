package com.coingazua.zotminer.batch.transaction.delete.item;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.coingazua.zotminer.business.transaction.repository.TransactionsHistoryRepository;

@StepScope
public class DeleteTransactionTasklet implements Tasklet {
    @Autowired
    private TransactionsHistoryRepository transactionsHistoryRepository;
    
    @Value("${batch.deleteTransaction.deleteDate}")
    Integer deleteDateSetting;

    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    	Date deleteDate = Date.from(LocalDateTime.now().minusDays(deleteDateSetting).atZone(ZoneId.systemDefault()).toInstant());
    	// 설정일자 이전 데이터 전체 삭제
    	transactionsHistoryRepository.deleteByCreateDtLessThan(deleteDate);
        return RepeatStatus.FINISHED;
    }
}