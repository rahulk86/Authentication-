package com.auth.security;

import com.auth.dto.UserDto;
import com.auth.modal.user.User;
import com.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<User> {
    @Autowired
    private UserService userService;
    @Override
    @NonNull
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();
        User user =null;
        if (principal instanceof UserDto) {
            user =  userService.toUser((UserDto) principal);
        }
        if(user==null){
          return Optional.empty();
        }
        return Optional.of(user);
    }
}
