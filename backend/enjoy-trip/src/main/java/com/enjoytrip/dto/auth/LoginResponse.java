package com.enjoytrip.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    User user;
    @JsonProperty("token_pair")
    Token tokenPair;
}
