package com.coingazua.zotminer.business.user.service;

import java.util.List;

import com.coingazua.zotminer.business.user.repository.UserExchangeRepository;
import com.coingazua.zotminer.domain.user.entity.UserExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.coingazua.zotminer.business.exchange.repository.ExchangeRepository;
import com.coingazua.zotminer.domain.exchange.entity.Exchange;

@Service
public class UserExchangeService {
    @Autowired
    private UserExchangeRepository userExchangeRepository;

    @Cacheable(key = "#userSeq", value = "userExchange")
    public List<UserExchange> getUserExchange(Long userSeq) {
        return userExchangeRepository.findByUserSeq(userSeq);
    }
}
