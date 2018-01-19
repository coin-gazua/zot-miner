package com.coingazua.zotminer.business.transaction.repository;

import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionsHistoryRepository extends JpaRepository<TransactionsHistory, Long> {

}
