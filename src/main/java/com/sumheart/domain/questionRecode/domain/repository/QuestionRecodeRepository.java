package com.sumheart.domain.questionRecode.domain.repository;

import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.questionRecode.domain.QuestionRecode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRecodeRepository extends JpaRepository<QuestionRecode, Long> {

  Optional<QuestionRecode> findTopByFamilyOrderByCreatedAtDesc(Family family);
  List<QuestionRecode> findAllByFamily(Family family);

}
