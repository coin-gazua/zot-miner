package com.coingazua.zotminer.batch.transaction.delete;

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
import com.coingazua.zotminer.batch.transaction.delete.item.DeleteTransactionTasklet;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class DeleteTransactionBatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SimpleJobLauncher jobLauncher;

    @Scheduled(cron = "0 59 23 ? * *")
    public void perform() throws Exception {
        JobParameters param = new JobParametersBuilder().addString(JobNameEnum.DELETE_TRANSACTION.name(),
                String.valueOf(System.currentTimeMillis())).toJobParameters();
        jobLauncher.run(deleteTransactionJob(), param);
    }

    @Bean
    public DeleteTransactionTasklet deleteTransactionTasklet() {
        return new DeleteTransactionTasklet();
    }

    @Bean
    public Job deleteTransactionJob() {
        return jobBuilderFactory.get(JobNameEnum.DELETE_TRANSACTION.name())
                .start(deleteTransactionStep())
                .build();
    }

    @Bean
    public Step deleteTransactionStep() {
        return stepBuilderFactory.get(StepNameEnum.DELETE_TRANSACTION_1.name())
                .tasklet(deleteTransactionTasklet())
                .build();
    }
}
