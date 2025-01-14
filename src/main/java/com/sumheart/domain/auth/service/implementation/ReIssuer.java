package com.sumheart.domain.auth.service.implementation;

import com.sumheart.common.jwt.exception.InvalidTokenException;
import com.sumheart.common.jwt.exception.RefreshTokenNotFoundException;
import com.sumheart.common.jwt.util.JwtUtil;
import com.sumheart.domain.auth.domain.repository.RefreshRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReIssuer {

    private final JwtUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    public void reissue(HttpServletRequest request, HttpServletResponse response) {

        String refresh = jwtUtil.getTokenFromCookies(request, "refresh_normal", "refresh_social");

        if (refresh == null) {
            log.warn("Refresh token not found in cookies");
            throw new RefreshTokenNotFoundException();
        }

        jwtUtil.isExpiredRefresh(refresh, response);

        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refresh")) {
            log.warn("Invalid token category: {}", category);
            throw new InvalidTokenException();
        }

        Boolean isExist = refreshRepository.existsByRefreshToken(refresh);

        if (!isExist) {
            log.warn("Refresh token not found in database: {}", refresh);
            throw new RefreshTokenNotFoundException();
        }

        String loginType = jwtUtil.getLoginType(refresh);
        String accessCookieName = "access_" + loginType;
        String refreshCookieName = "refresh_" + loginType;

        Long id = jwtUtil.getId(refresh);

        String newAccess = jwtUtil.createAccessToken(id, loginType);
        String newRefresh = jwtUtil.createRefreshToken(id, loginType);

        refreshRepository.deleteByRefreshToken(refresh);
        jwtUtil.addRefreshToken(id, newRefresh);

        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createAccessCookie(accessCookieName, newAccess).toString());
        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createRefreshCookie(refreshCookieName, newRefresh).toString());
    }
}
