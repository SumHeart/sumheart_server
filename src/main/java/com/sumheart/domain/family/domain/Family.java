package com.sumheart.domain.family.domain;

import com.sumheart.domain.question.domain.Question;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Family {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "questionId", nullable = true)
  private Question lastQuestion;

  @NotNull
  @Size(min = 6, max = 6, message = "초대 코드는 반드시 6자리여야 합니다.")
  @Pattern(regexp = "^[A-Za-z0-9]{6}$", message = "초대 코드는 6자리 영문/숫자로 구성되어야 합니다.")
  private String invitationCode;

  private Date familyDay;

  @Column(nullable = false)
  private long totalDays = 0;

  public Family(String invitationCode) {
    this.invitationCode = invitationCode;
    this.totalDays = calculateDaysSinceFamilyDay();
  }

  public void updateLastQuestion(Question question) {
    this.lastQuestion = question;
  }

  public long calculateDaysSinceFamilyDay() {
    LocalDate today = LocalDate.now();
    LocalDate familyDate = familyDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    return ChronoUnit.DAYS.between(familyDate, today);
  }

  public void updateFamilyDay(Date familyDay) {
    this.familyDay = familyDay;
  }

  public void updateTotalDays() {
    this.totalDays = calculateDaysSinceFamilyDay();
  }
}
