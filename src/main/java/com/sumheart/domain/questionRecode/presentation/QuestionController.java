package com.sumheart.domain.questionRecode.presentation;

import com.sumheart.domain.answer.presentation.dto.AnswerRequest;
import com.sumheart.domain.answer.presentation.dto.AnswerResponse;
import com.sumheart.domain.answer.service.CommandAnswerService;
import com.sumheart.domain.answer.service.QueryAnswerService;
import com.sumheart.domain.questionRecode.presentation.dto.QuestionResponse;
import com.sumheart.domain.questionRecode.service.QueryQuestionRecodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sumheart.common.jwt.util.AuthenticationUtil.getMemberId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

  private final QueryQuestionRecodeService queryQuestionRecodeService;
  private final CommandAnswerService commandAnswerService;
  private final QueryAnswerService queryAnswerService;

  @GetMapping("/{question-id}")
  public QuestionResponse getQuestion(@PathVariable("question-id") Long questionId) {
    return QuestionResponse.from(queryQuestionRecodeService.getOne(questionId));
  }

  @GetMapping
  public List<QuestionResponse> getQuestions() {
    return queryQuestionRecodeService.getQuestionRecodes(getMemberId()).stream()
        .map(QuestionResponse::from)
        .toList();
  }

  @GetMapping("/{question-id}/answer")
  public List<AnswerResponse> getAllAnswer(@PathVariable("question-id") Long questionId) {
    return queryAnswerService.findAllByQuestionAndFamily(questionId, getMemberId()).stream()
        .map(AnswerResponse::from)
        .toList();
  }

  @PostMapping("/{question-id}/answer")
  public void createAnswer(@PathVariable("question-id") Long questionId, AnswerRequest answerRequest) {
    commandAnswerService.create(questionId, answerRequest.content(), answerRequest.weatherStickerCode(), getMemberId());
  }
}
