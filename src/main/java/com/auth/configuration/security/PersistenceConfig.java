package com.auth.configuration.security;

import com.auth.modal.user.User;
import com.auth.security.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Configuration
public class PersistenceConfig {
    private AppProperties appProperties;
    @Bean
    AuditorAware<User> auditorProvider() {
        return new AuditorAwareImpl();
    }
    @Bean
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(LocalDateTime.now(ZoneId.of(appProperties.getZoneId())));
    }
}
