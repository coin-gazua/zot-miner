package com.coingazua.zotminer.api.bithumb.client;

import com.coingazua.zotminer.api.bithumb.BithumbResponseCode;
import com.coingazua.zotminer.api.bithumb.model.RecentTransaction;
import com.coingazua.zotminer.api.bithumb.model.RecentTransactionResponse;
import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by uienw00 on 2018. 1. 22..
 */
public class BithumbApi {
    @Autowired
    private RestTemplate restTemplate;

    private static String baseUrl;

    @Value("${api.base.url.bithumb}")
    public void setBaseUrl(String baseUrl){
        this.baseUrl = baseUrl;
    }

    private enum ApiUrl{
        RECENT_TRANSACTIONS(baseUrl + "/public/recent_transactions/%s");

        private final String value;
        ApiUrl(final String value){
            this.value = value;
        }
    }

    public List<TransactionsHistory> recentTransaction(Long exchangeSeq, Currency currency){
        String uri = String.format(ApiUrl.RECENT_TRANSACTIONS.value, currency.name());
        RecentTransactionResponse response = restTemplate.getForObject(uri, RecentTransactionResponse.class);
        List<RecentTransaction> recentTransactions = getResult(response.getStatus(), response.getData());
        return recentTransactions.stream().map(recentTransaction -> recentTransaction.convertTransactionsHistory(exchangeSeq))
                .collect(Collectors.toList());
    }

    public <T> T getResult(String status, T body){
        BithumbResponseCode responseCode = BithumbResponseCode.get(status);
        if(responseCode == null){
            throw new NullPointerException("정의되지 않은 코드");
        }else if(!BithumbResponseCode.SUCCESS.equals(responseCode)){
            throw new RestClientException(responseCode.name());
        }
        return body;
    }
}
