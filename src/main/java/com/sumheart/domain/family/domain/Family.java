package com.sumheart.domain.family.domain;

import com.sumheart.domain.pet.domain.Pet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Family {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(min = 6, max = 6, message = "초대 코드는 반드시 6자리여야 합니다.")
  @Pattern(regexp = "^[A-Za-z0-9]{6}$", message = "초대 코드는 6자리 영문/숫자로 구성되어야 합니다.")
  private String invitationCode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "petId")
  private Pet pet;

  @NotNull
  private Date familyDay;

  @NotNull
  private long totalDays;

  @Builder
  public Family(String invitationCode, Date familyDay, long totalDay, Pet pet) {
    this.invitationCode = invitationCode;
    this.familyDay = familyDay;
    this.totalDays = totalDay;
    this.pet = pet;
  }

  public void updateTotalDays(long totalDays) {
    this.totalDays = totalDays;
  }
}
