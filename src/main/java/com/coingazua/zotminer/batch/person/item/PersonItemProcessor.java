package com.coingazua.zotminer.batch.person.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.coingazua.zotminer.home.entity.MinerTest;

public class PersonItemProcessor implements ItemProcessor<MinerTest, MinerTest> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public MinerTest process(final MinerTest person) throws Exception {
        final String test = person.getTest_str().toUpperCase() + "-----------------";
        person.setTest_str(test);
        log.info(person.toString());
        return person;
    }
}
