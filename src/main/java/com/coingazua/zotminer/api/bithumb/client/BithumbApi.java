package com.coingazua.zotminer.api.bithumb.client;

import com.coingazua.zotminer.api.bithumb.model.RecentTransaction;
import com.coingazua.zotminer.domain.common.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

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

    public void recentTransaction(Currency currency){
        String uri = String.format(ApiUrl.RECENT_TRANSACTIONS.value, currency.name());
        RecentTransaction result = restTemplate.getForObject(uri, RecentTransaction.class);
        System.out.println(result.toString());
    }
}
