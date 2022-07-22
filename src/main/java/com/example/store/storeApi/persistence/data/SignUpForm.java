package com.example.store.storeApi.persistence.data;

import lombok.*;

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
