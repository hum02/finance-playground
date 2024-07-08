package com.openbank;

import com.openbank.web.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OAuthClient {

    @Value("${openbanking.redirectUri}")
    String redirectUri;

    @Value("${openbanking.state}")
    String state;

    @Value("${openbanking.clientId}")
    String clientId;

    @Value("${openbanking.clientSecret}")
    String clientSecret;

    private static RestClient restClient = RestClient.builder().build();

    public TokenResponse getAccessToken(String code) {
        String url = "https://testapi.openbanking.or.kr/oauth/2.0/token";

        // 요청 본문 데이터
        Map<String, String> requestBody = Map.of(
                "code", code,
                "client_id", clientId,
                "client_secret", clientSecret,
                "redirect_uri", redirectUri,
                "grant_type", "authorization_code"
        );

        String formEncodedBody = requestBody.entrySet().stream()
                .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "="
                        + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        return restClient.method(HttpMethod.POST)
                .uri(URI.create(url))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(formEncodedBody)
                .retrieve().body(TokenResponse.class);
    }

    public String getAuthUri() {
        return String.format(
                "https://testapi.openbanking.or.kr/oauth/2.0/authorize" +
                        "?response_type=code" +
                        "&client_id=%s" +
                        "&redirect_uri=%s" +
                        "&scope=login inquiry transfer" +
                        "&state=%s" +
                        "&auth_type=0",
                clientId, redirectUri, state
        );
    }
}
