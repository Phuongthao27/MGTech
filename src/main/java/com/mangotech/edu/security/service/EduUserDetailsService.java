package com.mangotech.edu.security.service;

import com.mangotech.edu.config.Constants;
import com.mangotech.edu.domain.UserEntity;
import com.mangotech.edu.repository.user.UserRepository;
import com.mangotech.edu.security.userdetail.EduUserDetail;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class EduUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Authenticating {}", username);

        return userRepository
            .findTopByUsername(username)
            .map(this::createSpringSecurityUser)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private EduUserDetail createSpringSecurityUser(UserEntity user) {
        if (user.isRemoved() || user.getStatus() == Constants.DEACTIVE) {
            throw new RuntimeException("User not found");
        }
        return new EduUserDetail(
            user.getUsername(),
            user.getPassword(),
            user.getSalt(),
            Collections.singleton(user.getDepartment().getRole())
        );
    }
}
