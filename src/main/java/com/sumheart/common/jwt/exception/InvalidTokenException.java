package com.sumheart.common.jwt.exception;

import com.sumheart.common.exception.security.SumHeartSecurityException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends SumHeartSecurityException {

    public InvalidTokenException() {
        super(HttpStatus.UNAUTHORIZED, "INVALID_TOKEN", "잘못된 토큰입니다.");
    }
}
