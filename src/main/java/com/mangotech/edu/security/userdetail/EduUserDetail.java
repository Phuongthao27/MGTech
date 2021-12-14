package com.mangotech.edu.security.userdetail;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
public class EduUserDetail implements UserDetails {
    private final String username;
    private final String password;
    private final String salt;
    private final Collection<GrantedAuthority> authorities;

    public EduUserDetail(String username, String password, String salt, Collection<String> authorities) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.authorities = authorities.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getSalt() {
        return salt;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
