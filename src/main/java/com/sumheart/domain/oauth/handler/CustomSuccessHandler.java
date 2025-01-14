package com.sumheart.domain.oauth.handler;

import com.sumheart.common.jwt.util.JwtUtil;
import com.sumheart.domain.oauth.service.dto.CustomOAuth2User;
import com.sumheart.domain.user.domain.Users;
import com.sumheart.domain.user.domain.repository.UserRepository;
import com.sumheart.domain.user.exception.UserNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Value("${server.url}")
    private String redirectUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{

        log.warn("오어스 성공 핸들러 authentication : "+authentication);
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        log.warn("오어스 성공 핸들러" + customUserDetails.toString());

        Long id = customUserDetails.getId();

        Users user = userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);
        boolean isNewUser = user == null || user.getUsername() == null;
        log.warn("오어스 성공 핸들러 중간 체크 : "+isNewUser);

        if (isNewUser) {
            if (user != null) {
                userRepository.save(user);
            }
        }


        String accessToken = jwtUtil.createAccessToken(id, "social");
        String refreshToken = jwtUtil.createRefreshToken(id, "social");

        jwtUtil.addRefreshToken(id, refreshToken);

        log.warn("소셜 로그인 필터 동작");

        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createAccessCookie("access_social", accessToken).toString());
        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createRefreshCookie("refresh_social", refreshToken).toString());

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.warn("쿠키 탐색 : "+cookie.getName());
                if ("JSESSIONID".equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    log.warn("JSESSIONID 발견");
                    break;
                }
            }
        }

        if (isNewUser) {
            log.warn("오어스 성공 핸들러 새로운 유저");
            response.sendRedirect(redirectUrl + "/additional-info");
        } else {
            log.warn("오어스 성공 핸들러 원래 있던 유저");
            response.sendRedirect(redirectUrl);
        }
    }

}
