package com.sumheart.domain.auth.presentation;

import com.sumheart.domain.auth.presentation.dto.AdditionalInfoRequest;
import com.sumheart.domain.auth.service.implementation.AdditionalInfoUpdater;
import com.sumheart.domain.auth.service.implementation.ReIssuer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.sumheart.common.jwt.util.AuthenticationUtil.getMemberId;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final ReIssuer reIssuer;
    private final AdditionalInfoUpdater additionalInfoUpdater;

    @PostMapping("/reissue")
    @ResponseStatus(HttpStatus.OK)
    public void reissue(
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        reIssuer.reissue(request, response);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public void updateAdditionalInfo(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody AdditionalInfoRequest additionalInfoRequest
    ) {
        additionalInfoUpdater.update(request, response, getMemberId(), additionalInfoRequest.username(), additionalInfoRequest.familyDay(), additionalInfoRequest.petName());
    }

    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public void checkAuthStatus() {
        log.warn("AuthController : /check 성공");
    }

}
