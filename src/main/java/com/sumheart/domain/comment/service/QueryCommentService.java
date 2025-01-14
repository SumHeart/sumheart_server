package com.sumheart.domain.comment.service;

import com.sumheart.domain.comment.domain.Comment;
import com.sumheart.domain.comment.domain.repository.CommentRepository;
import com.sumheart.domain.questionRecode.domain.QuestionRecode;
import com.sumheart.domain.questionRecode.service.QueryQuestionRecodeService;
import com.sumheart.domain.user.domain.Users;
import com.sumheart.domain.user.service.QueryUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryCommentService {

  private final CommentRepository commentRepository;
  private final QueryUserService queryUserService;
  private final QueryQuestionRecodeService queryQuestionRecodeService;

  public List<Comment> findAllByQuestion(Long questionRecodeId, Long userId) {
    QuestionRecode questionRecode = queryQuestionRecodeService.getOne(questionRecodeId);
    Users user = queryUserService.getOne(userId);
    return commentRepository.findAllByQuestionRecodeAndFamily(questionRecode, user.getFamily());
  }
}
