package com.coingazua.zotminer.configuration;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"com.coingazua.zotminer"})
public class JpaConfiguration {
    @Autowired
    DataSourceConfiguration dataSourceConfiguration;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Value("${spring.jpa.hibernate.dialect}")
    private String dialect;

    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private String formatSql;

    @Value("${spring.jpa.properties.hibernate.show_sql}")
    private String showSql;

    @Value("${spring.jpa.properties.hibernate.use_sql_comments}")
    private String useSqlComments;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.coingazua.zotminer");
        localContainerEntityManagerFactoryBean.setDataSource(dataSourceConfiguration.zotMinerDataSource());
        localContainerEntityManagerFactoryBean.setJpaProperties(additionalProperties());
        return localContainerEntityManagerFactoryBean;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.format_sql", formatSql);
        properties.setProperty("hibernate.show_sql", showSql);
        properties.setProperty("hibernate.use_sql_comments", useSqlComments);
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}