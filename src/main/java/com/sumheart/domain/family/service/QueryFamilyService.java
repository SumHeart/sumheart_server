package com.sumheart.domain.family.service;

import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.family.domain.repository.FamilyRepository;
import com.sumheart.domain.family.exception.FamilyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryFamilyService {

  private final FamilyRepository familyRepository;

  public List<Family> getAll() {
    return familyRepository.findAll();
  }

  public Family findIdByCode(String code) {
    return familyRepository.findIdByInvitationCode(code)
        .orElseThrow(FamilyNotFoundException::new);
  }
}