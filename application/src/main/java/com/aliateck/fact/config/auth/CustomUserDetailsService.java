package com.aliateck.fact.config.auth;

import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    UserApiService userApiService;

    CustomUserDetailsService(UserApiService userApiService) {
        this.userApiService = userApiService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userApiService.loadUserByUsername(username);
    }
}
