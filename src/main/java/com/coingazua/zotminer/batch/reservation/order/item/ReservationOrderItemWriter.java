package com.coingazua.zotminer.batch.reservation.order.item;

import java.util.List;

import com.coingazua.zotminer.api.bithumb.client.BithumbApi;
import com.coingazua.zotminer.api.bithumb.model.BalanceInfo;
import com.coingazua.zotminer.batch.reservation.order.model.ExchangeOrder;
import com.coingazua.zotminer.business.order.repository.OrderRepository;
import com.coingazua.zotminer.business.reservation.repository.ReservationOrderRepository;
import com.coingazua.zotminer.domain.order.entity.Order;
import com.coingazua.zotminer.domain.reservation.entity.ReservationOrder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.coingazua.zotminer.business.transaction.repository.TransactionsHistoryRepository;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;

public class ReservationOrderItemWriter implements ItemWriter<ExchangeOrder> {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ReservationOrderRepository reservationOrderRepository;

    @Autowired
    private BithumbApi bithumbApi;

    @Override
    public void write(List<? extends ExchangeOrder> exchangeOrders) throws Exception {
        exchangeOrders.forEach(exchangeOrder -> {
            //지갑 정보 api 호출
            BalanceInfo balanceInfo = bithumbApi.balanceInfo(exchangeOrder);

            String orderId = "";
            save(exchangeOrder, orderId);
        });

        System.out.println("=======================================writer========="+exchangeOrders.toString());
    }

    private void save(ExchangeOrder exchangeOrder, String orderId){
        ReservationOrder reservationOrder = exchangeOrder.getReservationOrder();
        Order order = reservationOrder.createOrder(orderId);
        orderRepository.save(order);

        reservationOrder.setOrder(true);
        reservationOrderRepository.save(reservationOrder);
    }
}

