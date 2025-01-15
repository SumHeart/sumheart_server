package com.sumheart.domain.family.presentation.dto;

import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.user.domain.Users;
import com.sumheart.domain.user.presentation.dto.UsernameResponse;

import java.util.List;

public record GetFamilyResponse(

    long totalDay,
    List<UsernameResponse> families,
    long myPoint
) {
  public static GetFamilyResponse of(Family family, List<Users> users, Users user) {
    List<UsernameResponse> userNameResponses = users.stream()
        .map(UsernameResponse::from)
        .toList();
    return new GetFamilyResponse(family.getTotalDays(), userNameResponses, user.getPoint());
  }
}
