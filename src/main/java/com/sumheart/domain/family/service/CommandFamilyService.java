package com.sumheart.domain.family.service;

import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.family.domain.repository.FamilyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandFamilyService {

  private final FamilyRepository familyRepository;
  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  @Scheduled(cron = "0 0 0 * * *")
  public void updateTotalDays() {
    List<Family> families = familyRepository.findAll();
    for (Family family : families) {
      family.updateTotalDays();
      familyRepository.save(family);
    }
  }
}
