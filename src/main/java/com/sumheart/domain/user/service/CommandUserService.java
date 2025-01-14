package com.sumheart.domain.user.service;

import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.user.domain.Users;
import com.sumheart.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandUserService {

  private final UserRepository userRepository;
  private final QueryUserService queryUserService;

  public void updateFamily(Family family, Users user) {
    user.updateFamily(family);
    userRepository.save(user);
  }

  public void deleteFamily(Long userId) {
    Users user = queryUserService.getOne(userId);
    user.updateFamily(null);
    user.updateName(null);
    userRepository.save(user);
  }
}
