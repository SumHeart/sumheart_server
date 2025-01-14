package com.sumheart.domain.comment.presentation.dto;

import com.sumheart.domain.comment.domain.Comment;

public record CommentResponse(
    Long commentId,
    Long questionId,
    String questionContent,
    Long userId,
    String content
) {
  public static CommentResponse from(Comment comment) {
    return new CommentResponse(
        comment.getId(),
        comment.getQuestionRecode().getQuestion().getId(),
        comment.getQuestionRecode().getQuestion().getContent(),
        comment.getUser().getId(),
        comment.getContent()
        );
  }
}
