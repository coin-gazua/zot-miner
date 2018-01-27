package com.coingazua.zotminer.business.user.repository;

import java.util.List;

import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.user.entity.UserExchange;


public interface UserExchangeRepositoryCustom {
    List<UserExchange> getUserExchange(boolean isUse);
}
