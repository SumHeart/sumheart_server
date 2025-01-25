package com.sumheart.domain.family.service;

import com.sumheart.domain.pet.domain.Pet;
import com.sumheart.domain.pet.service.CommandPetService;
import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.family.domain.repository.FamilyRepository;
import com.sumheart.domain.questionRecode.service.CommandQuestionRecodeService;
import com.sumheart.domain.user.domain.Users;
import com.sumheart.domain.user.service.CommandUserService;
import com.sumheart.domain.user.service.QueryUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandFamilyService {

  private final FamilyRepository familyRepository;
  private final QueryFamilyService queryFamilyService;
  private final CommandUserService commandUserService;
  private final QueryUserService queryUserService;
  private final CommandQuestionRecodeService commandQuestionRecodeService;
  private final CommandPetService commandPetService;
  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  public Family create(Date familyDay, String petName) {
    Pet pet = commandPetService.create(petName);
    Family family = Family.builder()
        .invitationCode(createInvitationCode())
        .familyDay(familyDay)
        .totalDay(getSinceFamilyDay(familyDay))
        .pet(pet)
        .build();
    commandQuestionRecodeService.create(family);
    return familyRepository.save(family);
  }

  public void delete(Family family) {
    familyRepository.delete(family);
  }

  public void joinFamily(String code, Long userId) {
    Users user = queryUserService.getOne(userId);
    Family family = queryFamilyService.findIdByCode(code);
    delete(user.getFamily());
    commandUserService.updateFamily(family, user);
  }

  @Scheduled(cron = "0 0 0 * * *")
  public void updateTotalDays() {
    List<Family> families = familyRepository.findAll();
    families.forEach(family -> family.updateTotalDays(getSinceFamilyDay(family.getFamilyDay())));
    familyRepository.saveAll(families);
  }

  public long getSinceFamilyDay(Date familyDay) {
    LocalDate today = LocalDate.now();
    LocalDate familyDate = familyDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    return ChronoUnit.DAYS.between(familyDate, today) + 1;
  }

  public String createInvitationCode() {
    Random random = new Random();
    StringBuilder invitationCode = new StringBuilder(6);

    for (int i = 0; i < 6; i++) {
      int randomIndex = random.nextInt(CHARACTERS.length());
      invitationCode.append(CHARACTERS.charAt(randomIndex));
    }

    return invitationCode.toString();
  }
}
