package com.coingazua.zotminer.api.bithumb.client;

import com.coingazua.zotminer.api.bithumb.BithumbResponse;
import com.coingazua.zotminer.api.bithumb.BithumbResponseCode;
import com.coingazua.zotminer.api.bithumb.model.BalanceInfo;
import com.coingazua.zotminer.api.bithumb.model.RecentTransaction;
import com.coingazua.zotminer.domain.common.model.Currency;
import com.coingazua.zotminer.domain.transaction.entity.TransactionsHistory;
import com.coingazua.zotminer.domain.user.entity.UserExchange;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by uienw00 on 2018. 1. 22..
 */
public class BithumbApi<T extends BithumbResponse> {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String HMAC_SHA512 = "HmacSHA512";


    @Autowired
    private RestTemplate restTemplate;


    @Value("${api.base.url.bithumb}")
    public String baseUrl;

    private enum ApiUrl {
        RECENT_TRANSACTIONS("/public/recent_transactions/%s"),
        BALANCE_INFO("/info/balance");

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
        String url = baseUrl + String.format(ApiUrl.RECENT_TRANSACTIONS.value, currency.name());

        ResponseEntity<BithumbResponse<RecentTransaction>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<BithumbResponse<RecentTransaction>>() {
        });

        List<RecentTransaction> recentTransactions = getResult(response.getBody().getStatus(), response.getBody().getData());
        return recentTransactions.stream().map(recentTransaction -> recentTransaction.convertTransactionsHistory(exchangeSeq))
                .collect(Collectors.toList());
    }

    public BalanceInfo balanceInfo(UserExchange userExchange, Currency currency){
        BithumbApiClient api = new BithumbApiClient(baseUrl, userExchange.getApiKey(), userExchange.getSecretKey());

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("currency", currency.name());

        HashMap<String, String> result = api.callApi(ApiUrl.BALANCE_INFO.value, params);
        return new BalanceInfo(result, currency);
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
