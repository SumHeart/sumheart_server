package com.sumheart.domain.questionRecode.service;

import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.family.service.QueryFamilyService;
import com.sumheart.domain.question.domain.Question;
import com.sumheart.domain.question.service.QueryQuestionService;
import com.sumheart.domain.questionRecode.domain.QuestionRecode;
import com.sumheart.domain.questionRecode.domain.repository.QuestionRecodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommandQuestionRecodeService {

  private final QuestionRecodeRepository questionRecodeRepository;
  private final QueryQuestionRecodeService queryQuestionRecodeService;
  private final QueryQuestionService queryQuestionService;
  private final QueryFamilyService queryFamilyService;

  public void create(Question question, Family family) {
    QuestionRecode questionRecode = QuestionRecode.builder()
        .question(question)
        .family(family)
        .build();
    questionRecodeRepository.save(questionRecode);
  }

  public void create(Family family) {
    Question question = queryQuestionService.getOne(1L);
    QuestionRecode questionRecode = QuestionRecode.builder()
        .question(question)
        .family(family)
        .build();
    questionRecodeRepository.save(questionRecode);
  }

  @Scheduled(cron = "0 0 0 * * *")
  public void createQuestionRecode() {
    log.info("createQuestionRecode start");
    List<Family> families = queryFamilyService.getAll();
    for (Family family : families) {
      QuestionRecode questionRecode = queryQuestionRecodeService.getLastQuestionRecode(family);
      Question question = queryQuestionService.getNextQuestion(questionRecode);
      create(question, family);
    }
  }
}
