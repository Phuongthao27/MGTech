package com.mangotech.edu.security.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "edu.security.jwt")
public class JWTProperties {
    private String prefix;
}
