package com.openbank;

import com.openbank.web.TokenResponse;
import com.openbank.web.UserInfoResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Service
public class OpenBankingClient {

    public static final String BEARER = "Bearer";

    private static final RestClient restClient = RestClient.builder().build();

    public UserInfoResponse getUserInformation(String accessToken, String userSeqNo) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.scheme("https")
                        .host("testapi.openbanking.or.kr")
                        .path("/v2.0/user/me")
                        .queryParam("user_seq_no", userSeqNo)
                        .build())
                .header("Authorization", BEARER + accessToken)
                .retrieve().body(UserInfoResponse.class);
    }

    public TokenResponse getAccountBalance(String token) {
        String url = "https://openapi.openbanking.or.kr/v2.0/account/balance/fin_num";

        return restClient.method(HttpMethod.GET)
                .uri(URI.create(url))
                .header(HttpHeaders.AUTHORIZATION, BEARER + token)
                .retrieve().body(TokenResponse.class);

    }
}
