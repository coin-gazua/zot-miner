package com.coingazua.zotminer.api.bithumb.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RecentTransactionResponse {
    private String status;
    private List<RecentTransaction> data;
}
