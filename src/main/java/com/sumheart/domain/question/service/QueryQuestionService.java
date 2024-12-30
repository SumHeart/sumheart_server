package com.sumheart.domain.question.service;

import com.sumheart.domain.question.domain.repository.QuestionRepository;
import com.sumheart.domain.question.domain.Question;
import com.sumheart.domain.question.exception.QuestionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryQuestionService {

  private final QuestionRepository questionRepository;

  public Question findById(Long id) {
    return questionRepository.findById(id)
        .orElseThrow(QuestionNotFoundException::new);
  }
}
