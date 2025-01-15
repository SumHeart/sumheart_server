package com.sumheart.domain.comment.domain;

import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.questionRecode.domain.QuestionRecode;
import com.sumheart.domain.user.domain.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "questionRecodeId")
  private QuestionRecode questionRecode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "familyId")
  private Family family;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId")
  private Users user;

  @NotNull
  private String content;

  @Builder
  public Comment(QuestionRecode questionRecode, Family family, Users user, String content) {
    this.questionRecode = questionRecode;
    this.family = family;
    this.user = user;
    this.content = content;
  }
}
