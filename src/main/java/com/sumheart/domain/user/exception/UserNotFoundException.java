package com.sumheart.domain.user.exception;

import com.sumheart.common.exception.SumHeartException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends SumHeartException {
  public UserNotFoundException() {
    super(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다.");

  }
}
