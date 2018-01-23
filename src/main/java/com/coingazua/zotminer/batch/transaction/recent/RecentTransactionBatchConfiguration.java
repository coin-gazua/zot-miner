package com.coingazua.zotminer.batch.transaction.recent;

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
import com.coingazua.zotminer.batch.transaction.recent.item.RecentTransactionTasklet;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class RecentTransactionBatchConfiguration {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SimpleJobLauncher jobLauncher;

	@Scheduled(fixedRate = 1000)
	public void perform() throws Exception {
		JobParameters param = new JobParametersBuilder().addString(JobNameEnum.RECENT_TRANSACTION.name(),
		        String.valueOf(System.currentTimeMillis())).toJobParameters();
		jobLauncher.run(recentTransactionJob(), param);
	}

	@Bean
	public RecentTransactionTasklet recentTransactionTasklet() {
		return new RecentTransactionTasklet();
	}

	@Bean
	public Job recentTransactionJob() {
		return jobBuilderFactory.get(JobNameEnum.RECENT_TRANSACTION.name())
		        .start(recentTransactionStep())
		        .build();
	}

	@Bean
	public Step recentTransactionStep() {
		return stepBuilderFactory.get(StepNameEnum.RECENT_TRANSACTION_1.name())
		        .tasklet(recentTransactionTasklet())
		        .build();
	}
}
