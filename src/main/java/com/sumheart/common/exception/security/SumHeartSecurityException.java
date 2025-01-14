package com.sumheart.common.exception.security;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class SumHeartSecurityException extends AuthenticationException {
  private final HttpStatus status;
  private final String errorCode;

  public SumHeartSecurityException(HttpStatus status, String errorCode, String message) {
    super(message);
    this.status = status;
    this.errorCode = errorCode;
  }
}
