package com.sumheart.common.exception.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumheart.common.exception.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
public class SumHeartSecurityExceptionFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (SumHeartSecurityException e) {
      log.error("[SumHeart Security Exception] URI: {}, ErrorCode: {}, Message: {}",
          request.getRequestURI(), e.getErrorCode(), e.getMessage());
      handleSpaceSecurityException(response, e);
    } catch (AuthenticationException e) {
      log.error("[Authentication Exception] URI: {}, ErrorCode: {}, Message: {}",
          request.getRequestURI(), HttpStatus.UNAUTHORIZED, e.getMessage());
      handleAuthenticationException(response);
    }
  }

  private void handleSpaceSecurityException(HttpServletResponse response, SumHeartSecurityException e) throws IOException {
    response.setStatus(e.getStatus().value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding(StandardCharsets.UTF_8.name());

    ErrorResponse errorResponse = ErrorResponse.from(e.getStatus().value(), e.getErrorCode(), e.getMessage());

    response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
  }

  private void handleAuthenticationException(HttpServletResponse response) throws IOException {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding(StandardCharsets.UTF_8.name());

    ErrorResponse errorResponse = ErrorResponse.from(
        HttpStatus.UNAUTHORIZED.value(),
        "SECURITY_UNKNOWN",
        "시큐리티에서 알 수 없는 에러가 발생했습니다."
    );

    response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
  }
}