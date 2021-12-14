package com.mangotech.edu.service.jwt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountDto {
    private String username;
    private String role;
}
