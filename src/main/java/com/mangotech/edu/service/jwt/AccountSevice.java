package com.mangotech.edu.service.jwt;

import com.mangotech.edu.service.jwt.dto.AccountDto;
import com.mangotech.edu.service.jwt.dto.JWTTokenDto;
import com.mangotech.edu.service.jwt.input.LoginInput;
import com.mangotech.edu.service.jwt.input.RegisterInput;
import org.springframework.http.ResponseEntity;

public interface AccountSevice {
    ResponseEntity<JWTTokenDto> authorize(LoginInput login);

    ResponseEntity<AccountDto> register(RegisterInput register);
}
