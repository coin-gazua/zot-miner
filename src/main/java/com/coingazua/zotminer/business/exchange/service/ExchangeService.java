package com.coingazua.zotminer.business.exchange.service;

import com.coingazua.zotminer.business.exchange.repository.ExchangeRepository;
import com.coingazua.zotminer.domain.exchange.entity.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeService {
    @Autowired
    private ExchangeRepository exchangeRepository;

    @Cacheable(value = "exchange")
    public List<Exchange> getCacheableAllExchange() {
        return exchangeRepository.findAll();
    }
}
