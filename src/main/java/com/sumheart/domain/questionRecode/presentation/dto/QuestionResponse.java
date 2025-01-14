package com.sumheart.domain.questionRecode.presentation.dto;

import com.sumheart.domain.questionRecode.domain.QuestionRecode;

import java.time.LocalDateTime;

public record QuestionResponse(
    Long id,
    Long questionId,
    String content,
    LocalDateTime created
) {
  public static QuestionResponse from(QuestionRecode questionRecode) {
    return new QuestionResponse(
        questionRecode.getId(),
        questionRecode.getQuestion().getId(),
        questionRecode.getQuestion().getContent(),
        questionRecode.getCreatedAt());
  }
}
