package com.coingazua.zotminer.batch.orderbook;

import com.coingazua.zotminer.batch.orderbook.item.OrderBookItemProcessor;
import com.coingazua.zotminer.batch.orderbook.item.OrderBookItemReader;
import com.coingazua.zotminer.batch.orderbook.item.OrderBooktemWriter;
import com.coingazua.zotminer.home.entity.MinerTest;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class OrderBookBatchConfiguration {
    private static final String JOB_NAME = "orderBookJob";
    private static final String STEP_NAME = "orderBookStep";

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public OrderBookItemReader orderBookReader(){
        return new OrderBookItemReader();
    }

    @Bean
    public OrderBookItemProcessor orderBookProcessor(){
        return new OrderBookItemProcessor();
    }

    @Bean
    public OrderBooktemWriter orderBookWriter(){
        return new OrderBooktemWriter();
    }

    @Bean
    public Job orderBookJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(orderBookStep())
                .build();
    }

    @Bean
    public Step orderBookStep() {
        return stepBuilderFactory.get(STEP_NAME)
                .<MinerTest, MinerTest>chunk(1)
                .reader(orderBookReader())
                .processor(orderBookProcessor())
                .writer(orderBookWriter())
                .build();
    }
}
