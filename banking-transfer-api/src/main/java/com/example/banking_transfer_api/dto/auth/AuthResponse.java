package com.example.banking_transfer_api.dto.auth;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private String tokenType;
    private long expiresInMs;
    private String username;
    private String role;
}
