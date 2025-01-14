package com.sumheart.domain.oauth.service.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final UserDto userDto;

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        return collection;
    }

    @Override
    public String getName() {
        return String.valueOf(userDto.id());
    }

    public Long getId() {
        return userDto.id();
    }
}
