package com.coingazua.zotminer.domain.reservation.entity;

import java.util.Date;

import javax.persistence.*;

import com.coingazua.zotminer.common.util.DateUtil;
import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.exchange.entity.Exchange;
import com.coingazua.zotminer.domain.order.entity.Order;
import com.coingazua.zotminer.domain.order.model.OrderType;
import com.coingazua.zotminer.domain.order.model.OrderValueType;
import com.coingazua.zotminer.domain.reservation.model.ReservationType;
import com.coingazua.zotminer.domain.user.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(catalog = "zotMiner", name = "reservation_order")
@Getter
@Setter
@ToString(exclude = { "user", "exchange" })
public class ReservationOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq", nullable = false)
	private Long seq;

	@Column(name = "user_seq", nullable = false)
	private Long userSeq;

	@Column(name = "exchange_seq", nullable = false)
	private Long exchangeSeq;

	@Column(name = "currency", nullable = false)
	@Enumerated(EnumType.STRING)
	private Currency currency = Currency.XRP;

	@Column(name = "order_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderType orderType = OrderType.BID;

	@Column(name = "reservation_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private ReservationType reservationType = ReservationType.FIX;

	@Column(name = "is_high", nullable = false)
	private boolean isHigh = false;

	@Column(name = "order_value", nullable = false)
	private Long orderValue;

	@Column(name = "order_value_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderValueType orderValueType = OrderValueType.PRICE;

	@Column(name = "is_use", nullable = false)
	private boolean isUse = true;

	@Column(name = "is_order", nullable = false)
	private boolean isOrder = false;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dt", nullable = false)
	private Date createDt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq", insertable = false, updatable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exchange_seq", insertable = false, updatable = false)
	private Exchange exchange;

	public boolean isOrder(double targetValue) {
		if (this.isHigh) { //고가 예약주문
			//평균 체결가격 보다 같거나 크면 예약주문 진행
			if (this.orderValue <= targetValue) {
				return true;
			}
		} else { //저가 예약주문
			//평균 체결가격 보다 같거나 작으면 예약주문 진행
			if (this.orderValue >= targetValue) {
				return true;
			}
		}
		return false;
	}

	public Order createOrder(String orderId){
	    Order order = new Order();
	    order.setReservationOrderSeq(this.seq);
	    order.setUserSeq(this.userSeq);
	    order.setOrderId(orderId);
        order.setCreateDt(DateUtil.getTodayDateTime());
        return order;
    }
}
