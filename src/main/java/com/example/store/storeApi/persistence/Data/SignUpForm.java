package com.example.store.storeApi.persistence.Data;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpForm
{
    private String name;
    private String username;
    private String email;
    private String password;
    private String role;



}
