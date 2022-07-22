package com.example.store.storeApi.persistence.Data;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

    // Payload Validators
    private String email;
    private String password;
    private DeviceInfo deviceInfo;
}