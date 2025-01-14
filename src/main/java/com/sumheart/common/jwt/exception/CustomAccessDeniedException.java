package com.sumheart.common.jwt.exception;

import com.sumheart.common.exception.security.SumHeartSecurityException;
import org.springframework.http.HttpStatus;

public class CustomAccessDeniedException extends SumHeartSecurityException {
    public CustomAccessDeniedException() {
        super(HttpStatus.FORBIDDEN, "ACCESS_DENIED", "권한이 필요합니다.");
    }
}
