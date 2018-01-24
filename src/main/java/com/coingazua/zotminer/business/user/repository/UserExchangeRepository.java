package com.coingazua.zotminer.business.user.repository;

import com.coingazua.zotminer.domain.user.entity.UserExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserExchangeRepository extends JpaRepository<UserExchange, Long> {
    List<UserExchange> findByUserSeq(Long userSeq);
}
