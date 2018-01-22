package com.coingazua.zotminer.business.exchange.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.coingazua.zotminer.business.exchange.repository.ExchangeRepository;
import com.coingazua.zotminer.domain.exchange.entity.Exchange;

@Service
public class ExchangeService {
    @Autowired
    private ExchangeRepository exchangeRepository;

    @Cacheable(value = "exchange")
    public List<Exchange> getAllExchange(){
        return exchangeRepository.findAll();
    }
}
