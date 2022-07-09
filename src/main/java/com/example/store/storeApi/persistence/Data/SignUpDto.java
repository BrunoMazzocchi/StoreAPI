package com.example.store.storeApi.persistence.Data;

import lombok.*;

@Data
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
