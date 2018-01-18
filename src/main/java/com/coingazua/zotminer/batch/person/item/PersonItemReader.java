package com.coingazua.zotminer.batch.person.item;

import java.util.List;

import com.coingazua.zotminer.batch.person.model.Person;
import com.coingazua.zotminer.home.entity.MinerTest;
import com.coingazua.zotminer.home.repository.MinerTestRepository;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonItemReader implements ItemReader<MinerTest> {
    @Autowired
    private MinerTestRepository minerTestRepository;

    private List<MinerTest> personList;
    private int fetchCount = 0;

    @BeforeStep
    void beforeStep() {
        personList = minerTestRepository.findAll();
    }

    @Override
    public MinerTest read() {
        if(fetchCount < this.personList.size()){
            return this.personList.get(fetchCount++);
        }else{
            return null;
        }
    }


}

