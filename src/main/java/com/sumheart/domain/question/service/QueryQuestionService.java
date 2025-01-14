package com.sumheart.domain.question.service;

import com.sumheart.domain.question.domain.Question;
import com.sumheart.domain.question.domain.repository.QuestionRepository;
import com.sumheart.domain.question.exception.QuestionNotFoundException;
import com.sumheart.domain.questionRecode.domain.QuestionRecode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryQuestionService {

  private final QuestionRepository questionRepository;

  public Question getOne(Long id) {
    return questionRepository.findById(id)
        .orElseThrow(QuestionNotFoundException::new);
  }

  public Question getNextQuestion(QuestionRecode questionRecode) {
    return getOne(questionRecode.getQuestion().getId() + 1L);
  }
}
