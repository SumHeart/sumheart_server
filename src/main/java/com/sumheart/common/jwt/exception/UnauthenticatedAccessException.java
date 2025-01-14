package com.sumheart.common.jwt.exception;

import com.sumheart.common.exception.security.SumHeartSecurityException;
import org.springframework.http.HttpStatus;

public class UnauthenticatedAccessException extends SumHeartSecurityException {
    public UnauthenticatedAccessException() {
        super(HttpStatus.UNAUTHORIZED, "UNAUTHENTICATED_ACCESS", "인증이 필요합니다.");
    }
}