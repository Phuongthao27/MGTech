package com.mangotech.edu.config;

import com.mangotech.edu.security.AuthoritiesConstants;
import com.mangotech.edu.security.EduDaoAuthenticationProvider;
import com.mangotech.edu.security.jwt.JWTConfigurer;
import com.mangotech.edu.security.jwt.TokenProvider;
import com.mangotech.edu.security.properties.JWTProperties;
import com.mangotech.edu.security.service.EduUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;
import tech.jhipster.config.JHipsterProperties;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JHipsterProperties jHipsterProperties;
    private final TokenProvider tokenProvider;
    private final SecurityProblemSupport problemSupport;
    private final JWTProperties jwtProperties;

    public SecurityConfiguration(
        TokenProvider tokenProvider,
        JHipsterProperties jHipsterProperties,
        SecurityProblemSupport problemSupport, JWTProperties jwtProperties) {
        this.tokenProvider = tokenProvider;
        this.problemSupport = problemSupport;
        this.jHipsterProperties = jHipsterProperties;
        this.jwtProperties = jwtProperties;
    }

    @Bean
    public EduUserDetailsService EduUserDetailsService() {
        return new EduUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider AuthenticationProvider() {
        var authenticationProvider = new EduDaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(EduUserDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf()
            .disable()
            .exceptionHandling()
            .authenticationEntryPoint(problemSupport)
            .accessDeniedHandler(problemSupport)
            .and()
            .headers()
            .contentSecurityPolicy(jHipsterProperties.getSecurity().getContentSecurityPolicy())
            .and()
            .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
            .and()
            .featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; fullscreen 'self'; payment 'none'")
            .and()
            .frameOptions()
            .deny()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/account/login").permitAll()
            .antMatchers("/api/account/register").permitAll()
            .antMatchers("/api/admin/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/api/**").authenticated()
            .antMatchers("/management/health").permitAll()
            .antMatchers("/management/health/**").permitAll()
            .antMatchers("/management/info").permitAll()
            .antMatchers("/management/prometheus").permitAll()
            .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .and()
            .apply(securityConfigurerAdapter());
        // @formatter:on
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider, jwtProperties);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(AuthenticationProvider());
    }
}
