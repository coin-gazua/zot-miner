package com.coingazua.zotminer.batch.person;

import com.coingazua.zotminer.batch.person.item.PersonItemReader;
import com.coingazua.zotminer.batch.person.item.PersonItemWriter;
import com.coingazua.zotminer.batch.person.model.Person;
import com.coingazua.zotminer.batch.person.item.PersonItemProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonStepConfiguration {

    private static final String STEP_NAME = "StepName";

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public PersonItemReader reader(){
        return new PersonItemReader();
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public PersonItemWriter writer(){
        return new PersonItemWriter();
    }

    @Bean
    public Step Step() {
        return stepBuilderFactory.get(STEP_NAME)
                .<Person, Person>chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

}
