package com.coingazua.zotminer.batch.person.item;

import java.util.List;

import com.coingazua.zotminer.batch.person.model.Person;
import com.coingazua.zotminer.home.entity.MinerTest;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

public class PersonItemWriter implements ItemWriter<MinerTest> {

    @Override
    public void write(List<? extends MinerTest> _alphabet) throws Exception {
        for (MinerTest s : _alphabet) {
            System.out.println(s);
        }
    }
}

