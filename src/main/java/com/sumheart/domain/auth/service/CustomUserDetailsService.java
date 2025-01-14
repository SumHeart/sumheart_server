package com.sumheart.domain.auth.service;

import com.sumheart.domain.auth.service.dto.CustomUserDetails;
import com.sumheart.domain.user.domain.Users;
import com.sumheart.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Users user = userRepository.findByEmail(email);

        if (user != null) {

            return new CustomUserDetails(user);
        }

        return null;
    }
}
