package com.sumheart.domain.oauth.service;

import com.sumheart.domain.oauth.service.dto.*;
import com.sumheart.domain.user.domain.Users;
import com.sumheart.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        log.warn("오어스 서비스 registrationId : " + registrationId);

        KakaoResponse kakaoResponse = new KakaoResponse(oAuth2User.getAttributes());

        log.warn("오어스 서비스 oAuth2Response : " + kakaoResponse);

        Users existData = userRepository.findByEmail(kakaoResponse.getEmail());
        Long id;

        if (existData == null) {
            Users users = Users.builder()
                    .email(kakaoResponse.getEmail())
                    .build();
            userRepository.save(users);
            id = users.getId();
        } else {
            existData.updateEmail(kakaoResponse.getEmail());
            userRepository.save(existData);
            id = existData.getId();
        }

        UserDto userDto = UserDto.builder()
                .id(id)
                .build();
        log.warn("오어스 서비스 userDto : " + userDto.toString());
        return new CustomOAuth2User(userDto);
    }
}
