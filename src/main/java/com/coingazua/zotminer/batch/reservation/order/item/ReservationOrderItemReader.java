package com.coingazua.zotminer.batch.reservation.order.item;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.coingazua.zotminer.batch.reservation.order.model.ExchangeOrder;
import com.coingazua.zotminer.business.transaction.repository.TransactionsHistoryRepository;
import com.coingazua.zotminer.business.user.repository.UserExchangeRepository;
import com.coingazua.zotminer.business.user.repository.UserRepository;
import com.coingazua.zotminer.business.user.service.UserExchangeService;
import com.coingazua.zotminer.domain.user.entity.User;
import com.coingazua.zotminer.domain.user.entity.UserExchange;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.coingazua.zotminer.api.bithumb.client.BithumbApi;
import com.coingazua.zotminer.business.exchange.service.ExchangeService;
import com.coingazua.zotminer.business.reservation.repository.ReservationOrderRepository;
import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.exchange.entity.Exchange;
import com.coingazua.zotminer.domain.reservation.entity.ReservationOrder;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;
import org.springframework.beans.factory.annotation.Value;

@StepScope
public class ReservationOrderItemReader implements ItemReader<ExchangeOrder> {
	@Autowired
	private UserExchangeService userExchangeService;

	@Autowired
	private TransactionsHistoryRepository transactionsHistoryRepository;

    @Value("${batch.reservationOrder.transactionHistoryTime}")
    private Integer transactionHistoryTime;

	@Autowired
	private ReservationOrderRepository reservationOrderRepository;

	private List<ExchangeOrder> exchangeOrders;
	private int fetchCount;

	@BeforeStep
	void beforeStep(final StepExecution stepExecution) {
		//스케쥴로 계속 실행 되기 때문에 초기화 해줌
		fetchCount = 0;
        exchangeOrders = new ArrayList<>();

		//유효한 회원이 속한 거래소 목록 조회
		List<UserExchange> userExchanges = userExchangeService.getCacheableUserExchange(true);
		userExchanges.stream().forEach(userExchange -> {
		    //예약 주문 조회
			List<ReservationOrder> reservationOrders = reservationOrderRepository.findByUserSeqAndExchangeSeqAndIsOrderAndIsUse(userExchange.getUserSeq(),
			        userExchange.getExchangeSeq(), false, true);
            reservationOrders.stream().forEach(reservationOrder -> {
                //지난 10초간 평균 체결가격 조회
                Date searchDate = Date.from(LocalDateTime.now().minusSeconds(transactionHistoryTime).atZone(ZoneId.systemDefault()).toInstant());
                List<TransactionsHistory> transactionsHistories = transactionsHistoryRepository.findByCreateDtGreaterThanAndCurrency(searchDate, reservationOrder.getCurrency());
                double averageTotal = transactionsHistories.stream()
                        .mapToDouble(TransactionsHistory::getTotal)
                        .average()
                        .orElse(0);

                if(reservationOrder.isOrder(averageTotal)){
					exchangeOrders.add(new ExchangeOrder(userExchange.getApiKey(), userExchange.getSecretKey(), reservationOrder));
				}
            });
		});
	}

	@Override
	public ExchangeOrder read() {
		System.out.println("=========================================read==========================");
		if (fetchCount < this.exchangeOrders.size()) {
			return this.exchangeOrders.get(fetchCount++);
		} else {
			return null;
		}
	}
}
