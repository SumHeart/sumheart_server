package com.sumheart.domain.answer.service;

import com.sumheart.domain.answer.domain.Answer;
import com.sumheart.domain.answer.domain.repository.AnswerRepository;
import com.sumheart.domain.answer.domain.value.WeatherSticker;
import com.sumheart.domain.answer.exception.UserAnswerExistsException;
import com.sumheart.domain.questionRecode.domain.QuestionRecode;
import com.sumheart.domain.questionRecode.service.QueryQuestionRecodeService;
import com.sumheart.domain.user.domain.Users;
import com.sumheart.domain.user.service.QueryUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandAnswerService {

  private final AnswerRepository answerRepository;
  private final QueryUserService queryUserService;
  private final QueryQuestionRecodeService queryQuestionRecodeService;

  public void create(Long questionRecodeId, String content, long weatherStickerCode, Long userId) {
    boolean isDuplicateAnswer = answerRepository.existsByUserIdAndQuestionRecodeId(userId, questionRecodeId);
    if (isDuplicateAnswer) throw new UserAnswerExistsException();
    Users user = queryUserService.getOne(userId);
    QuestionRecode question = queryQuestionRecodeService.getOne(questionRecodeId);
    Answer answer = Answer.builder()
        .user(user)
        .family(user.getFamily())
        .questionRecode(question)
        .content(content)
        .weatherSticker(WeatherSticker.fromCode(weatherStickerCode))
        .build();
    answerRepository.save(answer);
  }
}
