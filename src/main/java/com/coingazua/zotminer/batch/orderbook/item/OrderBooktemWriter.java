package com.coingazua.zotminer.batch.orderbook.item;

import com.coingazua.zotminer.home.entity.MinerTest;
import com.coingazua.zotminer.home.repository.MinerTestRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

public class OrderBooktemWriter implements ItemWriter<MinerTest> {
    @Autowired
    private MinerTestRepository minerTestRepository;

    @Override
    public void write(List<? extends MinerTest> _alphabet) throws Exception {
        System.out.println("=========================================write==========================");
        for (MinerTest s : _alphabet) {
            minerTestRepository.save(s);
        }
    }
}

