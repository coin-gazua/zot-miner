package com.coingazua.zotminer.business.transaction.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;


public interface TransactionsHistoryRepository extends JpaRepository<TransactionsHistory, Long> {
	List<TransactionsHistory> deleteByCreateDtLessThan(Date date);
}