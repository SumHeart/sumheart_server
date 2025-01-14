package com.sumheart.domain.answer.presentation.dto;

public record AnswerRequest(
    String content,
    long weatherStickerCode
) {
}
