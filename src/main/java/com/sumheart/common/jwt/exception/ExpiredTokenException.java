package com.sumheart.common.jwt.exception;

import com.sumheart.common.exception.security.SumHeartSecurityException;
import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends SumHeartSecurityException {

    public ExpiredTokenException() {
        super(HttpStatus.UNAUTHORIZED, "EXPIRED_TOKEN", "만료된 토큰입니다.");
    }
}
