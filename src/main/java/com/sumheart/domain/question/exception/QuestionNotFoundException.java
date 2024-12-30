package com.sumheart.domain.question.exception;

import com.sumheart.common.exception.SumHeartException;
import org.springframework.http.HttpStatus;

public class QuestionNotFoundException extends SumHeartException {
  public QuestionNotFoundException() {
    super(HttpStatus.NOT_FOUND, "QUESTION_NOT_FOUND", "질문을 찾을 수 없습니다.");
  }
}
