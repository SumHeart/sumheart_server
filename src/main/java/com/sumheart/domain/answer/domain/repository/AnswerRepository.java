package com.sumheart.domain.answer.domain.repository;

import com.sumheart.domain.answer.domain.Answer;
import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.questionRecode.domain.QuestionRecode;
import com.sumheart.domain.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

  Answer findByQuestionRecode(QuestionRecode questionRecode);
  Answer findByQuestionRecodeAndUser(QuestionRecode questionRecode, Users user);
  @Query("SELECT DISTINCT a FROM Answer a JOIN a.questionRecode qr WHERE a.family = :family")
  List<Answer> findDistinctByQuestionRecode(@Param("family") Family family);
  List<Answer> findAllByQuestionRecodeAndFamily(QuestionRecode questionRecode, Family family);
  boolean existsByUserIdAndQuestionRecodeId(Long userId, Long questionRecodeId);
}
