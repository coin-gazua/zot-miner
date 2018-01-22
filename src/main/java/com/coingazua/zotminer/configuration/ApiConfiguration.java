package com.coingazua.zotminer.configuration;

import com.coingazua.zotminer.api.bithumb.client.BithumbApi;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;


@Configuration
public class ApiConfiguration {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public BithumbApi bithumbApi(){
        return new BithumbApi();
    }

}
