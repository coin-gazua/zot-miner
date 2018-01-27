package com.coingazua.zotminer.api.bithumb.client;

import com.coingazua.zotminer.api.bithumb.BithumbResponseCode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;


@SuppressWarnings("unused")
public class BithumbApiClient {
    protected String api_url;
    protected String api_key;
    protected String api_secret;

    public BithumbApiClient(String api_url, String api_key, String api_secret) {
        this.api_url = api_url;
        this.api_key = api_key;
        this.api_secret = api_secret;
    }


    private String request(String strHost, String strMemod, HashMap<String, String> rgParams, HashMap<String, String> httpHeaders) {
        String response = "";

        // SSL ����
        if (strHost.startsWith("https://")) {
            HttpRequest request = HttpRequest.get(strHost);
            // Accept all certificates
            request.trustAllCerts();
            // Accept all hostnames
            request.trustAllHosts();
        }

        if (strMemod.toUpperCase().equals("HEAD")) {
        } else {
            HttpRequest request = null;

            // POST/GET ����
            if (strMemod.toUpperCase().equals("POST")) {

                request = new HttpRequest(strHost, "POST");
                request.readTimeout(10000);

                System.out.println("POST ==> " + request.url());

                if (httpHeaders != null && !httpHeaders.isEmpty()) {
                    httpHeaders.put("api-client-type", "2");
                    request.headers(httpHeaders);
                    System.out.println(httpHeaders.toString());
                }
                if (rgParams != null && !rgParams.isEmpty()) {
                    request.form(rgParams);
                    System.out.println(rgParams.toString());
                }
            } else {
                request = HttpRequest.get(strHost
                        + Util.mapToQueryString(rgParams));
                request.readTimeout(10000);

                System.out.println("Response was: " + response);
            }

            if (request.ok()) {
                response = request.body();
            } else {
                response = "error : " + request.code() + ", message : "
                        + request.body();
            }
            request.disconnect();
        }

        return response;
    }


    private HashMap<String, String> getHttpHeaders(String endpoint, HashMap<String, String> rgData, String apiKey, String apiSecret) {

        String strData = Util.mapToQueryString(rgData).replace("?", "");
        String nNonce = Util.usecTime();

        strData = strData.substring(0, strData.length() - 1);


        System.out.println("1 : " + strData);

        strData = Util.encodeURIComponent(strData);

        HashMap<String, String> array = new HashMap<String, String>();


        String str = endpoint + ";" + strData + ";" + nNonce;
        //String str = "/info/balance;order_currency=BTC&payment_currency=KRW&endpoint=%2Finfo%2Fbalance;272184496";

        String encoded = Util.asHex(Util.hmacSha512(str, apiSecret));

        System.out.println("strData was: " + str);
        System.out.println("apiSecret was: " + apiSecret);
        array.put("Api-Key", apiKey);
        array.put("Api-Sign", encoded);
        array.put("Api-Nonce", String.valueOf(nNonce));

        return array;

    }

    @SuppressWarnings("unchecked")
    public HashMap<String, String> callApi(String endpoint, HashMap<String, String> params) {
        String rgResultDecode = "";
        HashMap<String, String> rgParams = new HashMap<String, String>();
        rgParams.put("endpoint", endpoint);

        if (params != null) {
            rgParams.putAll(params);
        }

        String api_host = api_url + endpoint;
        HashMap<String, String> httpHeaders = getHttpHeaders(endpoint, rgParams, api_key, api_secret);

        rgResultDecode = request(api_host, "POST", rgParams, httpHeaders);

        // json �Ľ�
        HashMap<String, Object> result = null;

        if (!rgResultDecode.startsWith("error")) {
            try {
                result = new ObjectMapper().readValue(rgResultDecode,
                        HashMap.class);

                if(!BithumbResponseCode.SUCCESS.getValue().equals(result.get("status"))){
                    System.out.println("==========api error:"+ result.get("status"));
                    throw new RuntimeException();
                }
            } catch (IOException e) {
                System.out.println("==========api objectMapper error");
                throw new RuntimeException();
            }
        }

        return Optional.ofNullable(result)
                .map(r -> (HashMap<String, String>)r.get("data"))
                .orElseThrow(() -> new NullPointerException());
    }
}
