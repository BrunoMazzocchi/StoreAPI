package com.example.store.storeApi.persistence.data;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogOutRequest {

    private DeviceInfo deviceInfo;
    private String token;
}