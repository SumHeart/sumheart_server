package com.sumheart.domain.user.domain;

import com.sumheart.domain.family.domain.Family;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "familyId")
  private Family family;

  private String username;
  private String email;
  private long point;

  @Builder
  public Users(String email) {
    this.email = email;
    this.point = 0;
  }

  public void updateEmail(String email) {
    this.email = email;
  }

  public void updateName(String name) {
    this.username = name;
  }

  public void updateFamily(Family family) {
    this.family = family;
  }
}
