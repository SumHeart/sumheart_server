package com.sumheart.domain.family.exception;

import com.sumheart.common.exception.SumHeartException;
import org.springframework.http.HttpStatus;

public class FamilyNotFoundException extends SumHeartException {
  public FamilyNotFoundException() {
    super(HttpStatus.NOT_FOUND, "FAMILY_NOT_FOUND", "가족을 찾을 수 없습니다.");
  }
}
