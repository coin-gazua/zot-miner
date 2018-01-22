package com.coingazua.zotminer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class ZotMinerApplication {

  /*  @Value("${spring.profiles.active}")
    private String profile;*/

    public static void main(String[] args) {
        SpringApplication.run(ZotMinerApplication.class, args);
    }

/*
    @Bean
    public CommandLineRunner runner() {
        return (a) -> {
            System.out.println("==================================profile:" + profile);
        };
    }*/
}
