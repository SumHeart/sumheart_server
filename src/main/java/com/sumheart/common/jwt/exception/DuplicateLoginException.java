package com.sumheart.common.jwt.exception;

import com.sumheart.common.exception.security.SumHeartSecurityException;
import org.springframework.http.HttpStatus;

public class DuplicateLoginException extends SumHeartSecurityException {

    public DuplicateLoginException() {
        super(HttpStatus.UNAUTHORIZED, "DUPLICATE_LOGIN", "이미 로그인한 상태입니다.");
    }
}
