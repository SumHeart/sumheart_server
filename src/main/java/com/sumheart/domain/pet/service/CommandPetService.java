package com.sumheart.domain.pet.service;

import com.sumheart.domain.pet.domain.Pet;
import com.sumheart.domain.pet.domain.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandPetService {

  private final PetRepository petRepository;

  public Pet create(String name) {
    Pet pet = Pet.builder()
        .name(name)
        .build();
    return petRepository.save(pet);
  }
}
