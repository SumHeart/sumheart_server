package com.sumheart.domain.answer.exception;

import com.sumheart.common.exception.SumHeartException;
import org.springframework.http.HttpStatus;

public class UserAnswerExistsException extends SumHeartException {

  public UserAnswerExistsException()  {
    super(HttpStatus.CONFLICT, "USER_ANSWER_EXISTED", "유저 답변이 이미 존재합니다.");
  }
}
