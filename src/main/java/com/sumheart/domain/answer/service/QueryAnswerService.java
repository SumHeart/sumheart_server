package com.sumheart.domain.answer.service;

import com.sumheart.domain.answer.domain.Answer;
import com.sumheart.domain.answer.domain.repository.AnswerRepository;
import com.sumheart.domain.questionRecode.domain.QuestionRecode;
import com.sumheart.domain.questionRecode.service.QueryQuestionRecodeService;
import com.sumheart.domain.user.domain.Users;
import com.sumheart.domain.user.service.QueryUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryAnswerService {

  private final AnswerRepository answerRepository;
  private final QueryQuestionRecodeService queryQuestionRecodeService;
  private final QueryUserService queryUserService;

  public Answer findByQuestionRecode(Long questionRecodeId) {
    QuestionRecode questionRecode = queryQuestionRecodeService.getOne(questionRecodeId);
    return answerRepository.findByQuestionRecode(questionRecode);
  }

  public List<Answer> findAllByQuestionAndFamily(Long questionRecodeId, Long userId) {
    Users user = queryUserService.getOne(userId);
    QuestionRecode questionRecode = queryQuestionRecodeService.getOne(questionRecodeId);
    return answerRepository.findAllByQuestionRecodeAndFamily(questionRecode, user.getFamily());
  }

  public List<Answer> findAll(Long userId) {
    Users user = queryUserService.getOne(userId);
    return answerRepository.findDistinctByQuestionRecode(user.getFamily());
  }
}
