package com.openbank;

import com.openbank.web.dto.AccountBalanceResponse;
import com.openbank.web.dto.UserInfoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.now;

@Service
public class OpenBankingClient {

    public static final String BEARER = "Bearer";
    public static final String FINTECH_USE_NUM = "123456789012345678901234";
    public static final String CODE_DELIMETER = "U";

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private static final RestClient restClient = RestClient.builder().build();

    @Value("${openbanking.organizationCode}")
    String organizationCode;

    public UserInfoResponse getUserInformation(String accessToken, String userSeqNo) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.scheme("https")
                        .host("testapi.openbanking.or.kr")
                        .path("/v2.0/user/me")
                        .queryParam("user_seq_no", userSeqNo)
                        .build()
                )
                .header("Authorization", BEARER + accessToken)
                .retrieve().body(UserInfoResponse.class);
    }

    public AccountBalanceResponse getAccountBalance(String token, String bankAccountId) {
        String currentTime = now().format(formatter);

        return restClient.get()
                .uri(uriBuilder -> uriBuilder.scheme("https")
                        .host("testapi.openbanking.or.kr")
                        .path("/v2.0/account/balance/fin_num")
                        .queryParam("fintech_use_num", FINTECH_USE_NUM)
                        .queryParam("bank_tran_id", organizationCode + CODE_DELIMETER + bankAccountId)
                        .queryParam("tran_dtime", currentTime)
                        .build()
                )
                .header(HttpHeaders.AUTHORIZATION, BEARER + token)
                .retrieve().body(AccountBalanceResponse.class);
    }
}
