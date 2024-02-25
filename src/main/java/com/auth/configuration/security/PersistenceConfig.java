package com.auth.security;

import com.auth.modal.user.User;
import com.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;

@Configuration
public class PersistenceConfig {
    @Autowired
    private UserService userService;
    @Bean
    AuditorAware<User> auditorProvider(Authentication authentication) {
        return new AuditorAwareImpl(userService,authentication);
    }
}
