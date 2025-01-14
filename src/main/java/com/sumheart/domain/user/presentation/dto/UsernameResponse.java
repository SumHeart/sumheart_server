package com.sumheart.domain.user.presentation.dto;

import com.sumheart.domain.user.domain.Users;

public record UsernameResponse(
    String username
) {
  public static UsernameResponse from(Users user) {
    return new UsernameResponse(user.getUsername());
  }
}
