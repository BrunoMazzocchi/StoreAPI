package com.example.store.storeApi.persistence.Data;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogOutRequest {

    private DeviceInfo deviceInfo;
    private String token;
}