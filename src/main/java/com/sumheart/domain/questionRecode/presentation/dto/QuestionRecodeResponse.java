package com.sumheart.domain.questionRecode.presentation.dto;

import com.sumheart.domain.questionRecode.domain.QuestionRecode;

import java.time.LocalDateTime;

public record QuestionRecodeResponse(
    Long QuestionRecodeId,
    Long questionId,
    String content,
    LocalDateTime created
) {
  public static QuestionRecodeResponse from(QuestionRecode questionRecode) {
    return new QuestionRecodeResponse(
        questionRecode.getId(),
        questionRecode.getQuestion().getId(),
        questionRecode.getQuestion().getContent(),
        questionRecode.getCreatedAt());
  }
}
