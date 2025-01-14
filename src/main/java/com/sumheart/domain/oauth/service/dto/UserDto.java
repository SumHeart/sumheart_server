package com.sumheart.domain.oauth.service.dto;

import lombok.Builder;

public record UserDto(
        Long id
) {
    @Builder
    public UserDto(Long id) {
        this.id = id;
    }
}
