package com.mangotech.edu.service.jwt.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginInput {
    private String username;
    private String password;
    private boolean rememberMe = false;
}
