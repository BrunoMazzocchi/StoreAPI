package com.example.store.storeApi.persistence.data;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenRefreshRequest {
    private String refreshToken;
}