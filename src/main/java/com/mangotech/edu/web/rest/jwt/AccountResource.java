package com.mangotech.edu.web.rest.jwt;

import com.mangotech.edu.security.jwt.JWTFilter;
import com.mangotech.edu.service.jwt.AccountSevice;
import com.mangotech.edu.service.jwt.dto.AccountDto;
import com.mangotech.edu.service.jwt.dto.JWTTokenDto;
import com.mangotech.edu.service.jwt.input.LoginInput;
import com.mangotech.edu.service.jwt.input.RegisterInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
public class AccountResource {
    private final AccountSevice accountSevice;

    @PostMapping("/login")
    public ResponseEntity<JWTTokenDto> authorize(@Valid @RequestBody LoginInput login) {
        return accountSevice.authorize(login);
    }

    @PostMapping("/register")
    public ResponseEntity<AccountDto> register(@Valid @RequestBody RegisterInput register) {
        return accountSevice.register(register);
    }
}
