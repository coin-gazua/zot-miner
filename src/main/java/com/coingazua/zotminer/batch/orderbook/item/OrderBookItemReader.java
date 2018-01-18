package com.coingazua.zotminer.batch.orderbook.item;

import java.util.List;

import com.coingazua.zotminer.home.entity.MinerTest;
import com.coingazua.zotminer.home.repository.MinerTestRepository;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

@StepScope
public class OrderBookItemReader implements ItemReader<MinerTest> {
    @Autowired
    private MinerTestRepository minerTestRepository;

    private List<MinerTest> personList;
    private int fetchCount = 0;

    @BeforeStep
    void beforeStep(final StepExecution stepExecution) {
        JobParameters parameters = stepExecution.getJobExecution().getJobParameters();
        System.out.println(parameters.getString("parameter"));
        personList = minerTestRepository.findAll();
    }

    @Override
    public MinerTest read() {
        if(fetchCount < this.personList.size()){
            return this.personList.get(fetchCount++);
        }else{
            return null;
        }
    }


}

