package com.sumheart.domain.auth.service.implementation;

import com.sumheart.common.jwt.exception.InvalidTokenException;
import com.sumheart.common.jwt.exception.RefreshTokenNotFoundException;
import com.sumheart.common.jwt.util.JwtUtil;
import com.sumheart.domain.auth.domain.repository.RefreshRepository;
import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.family.service.CommandFamilyService;
import com.sumheart.domain.user.domain.Users;
import com.sumheart.domain.user.domain.repository.UserRepository;
import com.sumheart.domain.user.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdditionalInfoUpdater {

  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;
  private final RefreshRepository refreshRepository;
  private final CommandFamilyService commandFamilyService;

  @Transactional
  public void update(HttpServletRequest request, HttpServletResponse response, Long userId, String username, Date familyDay) {
    Users user = userRepository.findById(userId)
        .orElseThrow(UserNotFoundException::new);
    Family family = commandFamilyService.create(familyDay);

    String refresh = jwtUtil.getTokenFromCookies(request, "refresh_social");

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

    user.updateName(username);
    user.updateFamily(family);

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
