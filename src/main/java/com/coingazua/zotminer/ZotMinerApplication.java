package com.coingazua.zotminer;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
public class ZotMinerApplication {

  /*  @Value("${spring.profiles.active}")
    private String profile;*/

    public static void main(String[] args) {
        SpringApplication.run(ZotMinerApplication.class, args);
    }

    @PersistenceContext
    EntityManager entityManager;

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(entityManager);
    }

/*
    @Bean
    public CommandLineRunner runner() {
        return (a) -> {
            System.out.println("==================================profile:" + profile);
        };
    }*/
}
