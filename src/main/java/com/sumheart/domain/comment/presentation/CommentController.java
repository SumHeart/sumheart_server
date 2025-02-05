package com.sumheart.domain.comment.presentation;

import com.sumheart.domain.comment.presentation.dto.CommentRequest;
import com.sumheart.domain.comment.presentation.dto.CommentResponse;
import com.sumheart.domain.comment.service.CommandCommentService;
import com.sumheart.domain.comment.service.QueryCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sumheart.common.jwt.util.AuthenticationUtil.getMemberId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class CommentController {

  private final CommandCommentService commandCommentService;
  private final QueryCommentService queryCommentService;

  @GetMapping("/{questionRecode-id}/comment")
  public List<CommentResponse> findAll(@PathVariable("questionRecode-id") Long questionId) {
    return queryCommentService.findAllByQuestion(questionId, getMemberId()).stream()
        .map(CommentResponse::from)
        .toList();
  }

  @PostMapping("/{questionRecode-id}/comment")
  public void createComment(@PathVariable("questionRecode-id") Long questionId, @RequestBody CommentRequest request) {
    commandCommentService.create(questionId, request.content(), getMemberId());
  }
}
