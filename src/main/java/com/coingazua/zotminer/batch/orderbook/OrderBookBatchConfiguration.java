package com.coingazua.zotminer.batch.orderbook;

import com.coingazua.zotminer.batch.orderbook.item.OrderBookItemProcessor;
import com.coingazua.zotminer.batch.orderbook.item.OrderBookItemReader;
import com.coingazua.zotminer.batch.orderbook.item.OrderBooktemWriter;
import com.coingazua.zotminer.home.entity.MinerTest;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class OrderBookBatchConfiguration {
	private static final String JOB_NAME = "orderBookJob";
	private static final String STEP_NAME = "orderBookStep";

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SimpleJobLauncher jobLauncher;

	@Scheduled(fixedRate = 5000)
	public void perform() throws Exception {
        System.out.println("=================================perform=============================");
        JobParameters param = new JobParametersBuilder().addString("JobID",
		        String.valueOf(System.currentTimeMillis())).toJobParameters();
		jobLauncher.run(orderBookJob(), param);
	}

	@Bean
	public OrderBookItemReader orderBookReader() {
		return new OrderBookItemReader();
	}

	@Bean
	public OrderBookItemProcessor orderBookProcessor() {
		return new OrderBookItemProcessor();
	}

	@Bean
	public OrderBooktemWriter orderBookWriter() {
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
		        .<MinerTest, MinerTest> chunk(1)
		        .reader(orderBookReader())
		        .processor(orderBookProcessor())
		        .writer(orderBookWriter())
		        .build();
	}
}
