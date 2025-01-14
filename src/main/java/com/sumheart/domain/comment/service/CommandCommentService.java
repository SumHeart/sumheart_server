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

@Service
@Transactional
@RequiredArgsConstructor
public class CommandCommentService {

  private final CommentRepository commentRepository;
  private final QueryUserService queryUserService;
  private final QueryQuestionRecodeService queryQuestionRecodeService;

  public void create(Long questionRecodeId, String content, Long userId) {
    QuestionRecode questionRecode = queryQuestionRecodeService.getOne(questionRecodeId);
    Users user = queryUserService.getOne(userId);
    Comment comment = new Comment(questionRecode, user.getFamily(), user, content);
    commentRepository.save(comment);
  }
}
