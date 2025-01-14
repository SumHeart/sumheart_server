package com.sumheart.domain.answer.presentation.dto;

import com.sumheart.domain.answer.domain.Answer;

public record AnswerResponse(
    Long Id,
    Long userId,
    Long familyId,
    Long questionId,
    String content
) {
  public static AnswerResponse from(Answer answer) {
    return new AnswerResponse(answer.getId(), answer.getUser().getId(), answer.getFamily().getId(), answer.getQuestionRecode().getQuestion().getId(), answer.getContent());
  }
}
