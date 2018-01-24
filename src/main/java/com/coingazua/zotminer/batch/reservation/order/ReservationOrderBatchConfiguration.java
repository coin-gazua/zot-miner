package com.coingazua.zotminer.batch.reservation.order;

import com.coingazua.zotminer.batch.reservation.order.item.ReservationOrderItemProcessor;
import com.coingazua.zotminer.batch.reservation.order.item.ReservationOrderItemReader;
import com.coingazua.zotminer.batch.reservation.order.item.ReservationOrderItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.coingazua.zotminer.batch.JobNameEnum;
import com.coingazua.zotminer.batch.StepNameEnum;

import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class ReservationOrderBatchConfiguration {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SimpleJobLauncher jobLauncher;

	@Scheduled(fixedRate = 1000)
	public void perform() throws Exception {
		/*JobParameters param = new JobParametersBuilder().addString(JobNameEnum.RESERVATION_ORDER.name(),
		        String.valueOf(System.currentTimeMillis())).toJobParameters();
		jobLauncher.run(reservationOrderJob(), param);*/
	}

	@Bean
	public ReservationOrderItemReader reservationOrderReader() {
		return new ReservationOrderItemReader();
	}

	@Bean
	public ReservationOrderItemProcessor reservationOrderProcessor() {
		return new ReservationOrderItemProcessor();
	}

	@Bean
	public ReservationOrderItemWriter reservationOrderWriter() {
		return new ReservationOrderItemWriter();
	}

	@Bean
	public Job reservationOrderJob() {
		return jobBuilderFactory.get(JobNameEnum.RESERVATION_ORDER.name())
		        .start(reservationOrderStep())
		        .build();
	}

	@Bean
	public Step reservationOrderStep() {
		return stepBuilderFactory.get(StepNameEnum.RESERVATION_ORDER_1.name())
		        .<TransactionsHistory, TransactionsHistory> chunk(1)
		        .reader(reservationOrderReader())
		        .processor(reservationOrderProcessor())
		        .writer(reservationOrderWriter())
		        .build();
	}
}
