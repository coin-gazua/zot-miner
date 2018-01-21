package com.coingazua.zotminer.business.user.repository;

import com.coingazua.zotminer.domain.user.entity.UserExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coingazua.zotminer.domain.user.entity.User;


public interface UserExchangeRepository extends JpaRepository<UserExchange, Long> {

}
