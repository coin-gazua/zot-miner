package com.coingazua.zotminer.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
public class DataSourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource zotMinerDataSource() {
        return DataSourceBuilder.create().build();
    }

}
