package com.mangotech.edu.security;

import com.mangotech.edu.uitls.HashUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHelper {
    private final PasswordEncoder passwordEncoder;

    public PasswordHelper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Pair<String, String> createPassword(String password) {
        final var salt = HashUtils.generateSalt();
        final var saltPassword = createPassword(password, salt);
        return Pair.of(salt, saltPassword);
    }

    public boolean matchPassword(String presentedPassword, String salt, String matchPassword) {
        final var saltPassword = salt + presentedPassword;
        return this.passwordEncoder.matches(saltPassword, matchPassword);
    }

    private String createPassword(String password, String salt) {
        return passwordEncoder.encode(salt + password);
    }
}
