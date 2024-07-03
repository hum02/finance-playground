package com.openbank.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse(@JsonProperty("access_token") String accessToken,
                            @JsonProperty("token_type") String tokenType,
                            @JsonProperty("refresh_token") String refreshToken,
                            @JsonProperty("expires_in") int expiresIn,
                            String scope, @JsonProperty("user_seq_no") String userSeqNo) {
}
