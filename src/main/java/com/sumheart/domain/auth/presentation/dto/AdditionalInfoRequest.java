package com.sumheart.domain.auth.presentation.dto;

import java.util.Date;

public record AdditionalInfoRequest(
    String username,
    Date familyDay
) {
}
