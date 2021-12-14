package com.mangotech.edu.service.jwt;

import com.mangotech.edu.repository.department.DepartmentRepository;
import com.mangotech.edu.repository.user.UserRepository;
import com.mangotech.edu.security.jwt.JWTFilter;
import com.mangotech.edu.security.jwt.TokenProvider;
import com.mangotech.edu.security.properties.JWTProperties;
import com.mangotech.edu.service.jwt.dto.AccountDto;
import com.mangotech.edu.service.jwt.dto.JWTTokenDto;
import com.mangotech.edu.security.PasswordHelper;
import com.mangotech.edu.service.jwt.input.LoginInput;
import com.mangotech.edu.service.jwt.input.RegisterInput;
import com.mangotech.edu.service.jwt.mapper.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountSevice {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JWTProperties jwtProperties;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;
    private final PasswordHelper passwordHelper;
    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public ResponseEntity<JWTTokenDto> authorize(LoginInput login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            login.getUsername(),
            login.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, login.isRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        String token = jwtProperties.getPrefix() + jwt;
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, token);
        return new ResponseEntity<>(new JWTTokenDto(token), httpHeaders, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<AccountDto> register(RegisterInput register) {
        var entity = accountMapper.inputToEntity(register);
        final var saltPasswordPair = passwordHelper.createPassword(register.getPassword());
        entity.setSalt(saltPasswordPair.getLeft());
        entity.setPassword(saltPasswordPair.getRight());
        var department = departmentRepository.findTopByRoleAndRemovedFalse(register.getRole())
            .orElseThrow(() -> new RuntimeException("Role not found"));
        entity.setDepartment(department);
        final var newEntity = userRepository.save(entity);
        return ResponseEntity.ok().body(accountMapper.entityToDto(newEntity));
    }
}
