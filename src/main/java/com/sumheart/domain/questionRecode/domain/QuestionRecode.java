package com.sumheart.domain.questionRecode.domain;

import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.question.domain.Question;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)

public class QuestionRecode {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "questionId")
  private Question question;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "familyId")
  private Family family;

  @CreatedDate
  private LocalDateTime createdAt;

  public QuestionRecode(Question question, Family family) {
    this.question = question;
    this.family = family;
  }
}