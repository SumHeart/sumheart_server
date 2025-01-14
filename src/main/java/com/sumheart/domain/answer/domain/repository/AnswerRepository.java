package com.sumheart.domain.answer.domain.repository;

import com.sumheart.domain.answer.domain.Answer;
import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.questionRecode.domain.QuestionRecode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

  List<Answer> findAllByQuestionRecodeAndFamily(QuestionRecode questionRecode, Family family);
  boolean existsByUserIdAndQuestionRecodeId(Long userId, Long questionRecodeId);
}
