package com.sumheart.domain.questionRecode.service;

import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.questionRecode.domain.QuestionRecode;
import com.sumheart.domain.questionRecode.domain.repository.QuestionRecodeRepository;
import com.sumheart.domain.questionRecode.exception.QuestionRecodeNotFoundException;
import com.sumheart.domain.user.domain.Users;
import com.sumheart.domain.user.service.QueryUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryQuestionRecodeService {

  private final QuestionRecodeRepository questionRecodeRepository;
  private final QueryUserService queryUserService;

  public QuestionRecode getOne(Long id) {
    return questionRecodeRepository.findById(id)
        .orElseThrow(QuestionRecodeNotFoundException::new);
  }

  public QuestionRecode getLastQuestionRecode(Family family) {
    return questionRecodeRepository.findTopByFamilyOrderByCreatedAtDesc(family)
        .orElseThrow(QuestionRecodeNotFoundException::new);
  }

  public List<QuestionRecode> getQuestionRecodes(Long userId) {
    Users user = queryUserService.getOne(userId);
    return questionRecodeRepository.findAllByFamily(user.getFamily());
  }
}
