package com.coingazua.zotminer.batch.transaction.recent;

import com.coingazua.zotminer.batch.JobNameEnum;
import com.coingazua.zotminer.batch.StepNameEnum;
import com.coingazua.zotminer.batch.transaction.recent.item.RecentTransactionItemProcessor;
import com.coingazua.zotminer.batch.transaction.recent.item.RecentTransactionItemReader;
import com.coingazua.zotminer.batch.transaction.recent.item.RecentTransactionItemWriter;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;
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

    @Scheduled(fixedRate = 5000)
    public void perform() throws Exception {
        System.out.println("=================================perform=============================");
        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();
        jobLauncher.run(recentTransactionJob(), param);
    }

    @Bean
    public RecentTransactionItemReader recentTransactionReader() {
        return new RecentTransactionItemReader();
    }

    @Bean
    public RecentTransactionItemProcessor recentTransactionProcessor() {
        return new RecentTransactionItemProcessor();
    }

    @Bean
    public RecentTransactionItemWriter recentTransactionWriter() {
        return new RecentTransactionItemWriter();
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
                .<TransactionsHistory, TransactionsHistory>chunk(1)
                .reader(recentTransactionReader())
                .processor(recentTransactionProcessor())
                .writer(recentTransactionWriter())
                .build();
    }
}
