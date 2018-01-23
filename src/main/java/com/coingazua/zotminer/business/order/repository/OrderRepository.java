package com.coingazua.zotminer.business.order.repository;

import com.coingazua.zotminer.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
