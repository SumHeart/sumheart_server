package com.sumheart.domain.questionRecode.exception;

import com.sumheart.common.exception.SumHeartException;
import org.springframework.http.HttpStatus;

public class QuestionRecodeNotFoundException extends SumHeartException {
  public QuestionRecodeNotFoundException() {
    super(HttpStatus.NOT_FOUND, "QUESTION_RECODE_NOT_FOUND", "질문기록을 찾을 수 없습니다.");
  }
}
