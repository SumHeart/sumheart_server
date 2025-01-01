package com.sumheart.domain.family.domain.repository;

import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family,Long> {

  Question findQuestionById(Long id);
}
