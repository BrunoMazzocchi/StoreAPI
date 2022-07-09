package com.example.store.storeApi.persistence.Data;

import lombok.*;

@Data
public class LoginDTO {
    private String usernameOrEmail;
    private String password;
}
