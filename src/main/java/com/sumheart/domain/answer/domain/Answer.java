package com.sumheart.domain.answer.domain;

import com.sumheart.domain.answer.domain.value.WeatherSticker;
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
public class Answer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId")
  private Users user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "familyId")
  private Family family;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "questionRecodeId")
  private QuestionRecode questionRecode;

  @Enumerated(EnumType.STRING)
  private WeatherSticker weatherSticker;

  @NotNull
  private String content;

  @Builder
  public Answer(Users user, Family family, QuestionRecode questionRecode, String content, WeatherSticker weatherSticker) {
    this.user = user;
    this.family = family;
    this.questionRecode = questionRecode;
    this.content = content;
    this.weatherSticker = weatherSticker;
  }
}
