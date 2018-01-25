package com.coingazua.zotminer.api.bithumb.client;

import com.coingazua.zotminer.api.bithumb.BithumbResponse;
import com.coingazua.zotminer.api.bithumb.BithumbResponseCode;
import com.coingazua.zotminer.api.bithumb.model.RecentTransaction;
import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by uienw00 on 2018. 1. 22..
 */
public class BithumbApi<T extends BithumbResponse> {
    @Autowired
    private RestTemplate restTemplate;

    private static String baseUrl;

    @Value("${api.base.url.bithumb}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private enum ApiUrl {
        RECENT_TRANSACTIONS(baseUrl + "/public/recent_transactions/%s"),
        BALANCE_INFO(baseUrl + "/info/balance");

        private final String value;

        ApiUrl(final String value) {
            this.value = value;
        }
    }

    /**
     * 최근 거래 내역 조회
     * @param exchangeSeq
     * @param currency
     * @return
     */
    public List<TransactionsHistory> recentTransaction(Long exchangeSeq, Currency currency) {
        String uri = String.format(ApiUrl.RECENT_TRANSACTIONS.value, currency.name());

        ResponseEntity<BithumbResponse<RecentTransaction>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<BithumbResponse<RecentTransaction>>() {
        });

        List<RecentTransaction> recentTransactions = getResult(response.getBody().getStatus(), response.getBody().getData());
        return recentTransactions.stream().map(recentTransaction -> recentTransaction.convertTransactionsHistory(exchangeSeq))
                .collect(Collectors.toList());
    }

    public Map<String, Object> balanceInfo(){
        String uri = ApiUrl.BALANCE_INFO.value;

        return restTemplate.getForObject(uri, HashMap.class);
    }



    public <T> T getResult(String status, T body) {
        BithumbResponseCode responseCode = BithumbResponseCode.get(status);
        if (responseCode == null) {
            throw new NullPointerException("정의되지 않은 코드");
        } else if (!BithumbResponseCode.SUCCESS.equals(responseCode)) {
            throw new RestClientException(responseCode.name());
        }
        return body;
    }
}
