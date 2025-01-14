package com.sumheart.domain.family.presentation;

import com.sumheart.domain.family.presentation.dto.GetFamilyResponse;
import com.sumheart.domain.family.presentation.dto.JoinFamilyRequest;
import com.sumheart.domain.family.service.CommandFamilyService;
import com.sumheart.domain.user.service.CommandUserService;
import com.sumheart.domain.user.service.QueryUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.sumheart.common.jwt.util.AuthenticationUtil.getMemberId;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/family")
  public class FamilyController {
  private final CommandFamilyService commandFamilyService;
  private final CommandUserService commandUserService;
  private final QueryUserService queryUserService;

  @GetMapping
  public GetFamilyResponse getFamily() {
    return queryUserService.getFamily(getMemberId());
  }

  @PostMapping
  public void joinFamily(@RequestBody JoinFamilyRequest request) {
    commandFamilyService.joinFamily(request.code(), getMemberId());
  }

  @DeleteMapping
  public void deleteFamily() {
    commandUserService.deleteFamily(getMemberId());
  }
}
