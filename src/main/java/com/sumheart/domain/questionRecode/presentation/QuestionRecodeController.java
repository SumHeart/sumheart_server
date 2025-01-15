package com.sumheart.domain.questionRecode.presentation;

import com.sumheart.domain.answer.presentation.dto.AnswerRequest;
import com.sumheart.domain.answer.presentation.dto.AnswerResponse;
import com.sumheart.domain.answer.service.CommandAnswerService;
import com.sumheart.domain.answer.service.QueryAnswerService;
import com.sumheart.domain.questionRecode.presentation.dto.QuestionRecodeResponse;
import com.sumheart.domain.questionRecode.service.QueryQuestionRecodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sumheart.common.jwt.util.AuthenticationUtil.getMemberId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionRecodeController {

  private final QueryQuestionRecodeService queryQuestionRecodeService;
  private final CommandAnswerService commandAnswerService;
  private final QueryAnswerService queryAnswerService;

  @GetMapping("/{questionRecode-id}")
  public QuestionRecodeResponse getQuestion(@PathVariable("questionRecode-id") Long questionRecodeId) {
    return QuestionRecodeResponse.from(queryQuestionRecodeService.getOne(questionRecodeId));
  }

  @GetMapping
  public List<QuestionRecodeResponse> getQuestions() {
    return queryQuestionRecodeService.getQuestionRecodes(getMemberId()).stream()
        .map(QuestionRecodeResponse::from)
        .toList();
  }

  @GetMapping("/{questionRecode-id}/answer")
  public List<AnswerResponse> getAllAnswer(@PathVariable("questionRecode-id") Long questionRecodeId) {
    return queryAnswerService.findAllByQuestionAndFamily(questionRecodeId, getMemberId()).stream()
        .map(AnswerResponse::from)
        .toList();
  }

  @PostMapping("/{questionRecode-id}/answer")
  public void createAnswer(@PathVariable("questionRecode-id") Long questionRecodeId, AnswerRequest request) {
    commandAnswerService.create(questionRecodeId, request.content(), request.weatherStickerCode(), getMemberId());
  }
}
