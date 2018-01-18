package com.coingazua.zotminer.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@Import(JpaConfiguration.class)
public class BatchConfiguration {

    @Autowired
    private DataSource zotMinerDataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public JobRepositoryFactoryBean jobRepositoryFactory()
            throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setTransactionManager(transactionManager);
        factory.setDataSource(zotMinerDataSource);
        factory.setIsolationLevelForCreate("ISOLATION_DEFAULT");
        factory.setDatabaseType("mysql");
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public JobRepository jobRepository(JobRepositoryFactoryBean factory) throws Exception {
        return factory.getObject();
    }

    @Bean
    public SimpleJobLauncher jobLauncher(JobRepository jobRepository){
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }
}
