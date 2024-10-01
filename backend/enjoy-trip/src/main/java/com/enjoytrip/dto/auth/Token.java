package com.enjoytrip.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
public class Token {
    private String token;

    @JsonProperty("refresh_token")
    private String refreshToken;
}
