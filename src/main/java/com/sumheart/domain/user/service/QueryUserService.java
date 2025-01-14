package com.sumheart.domain.user.service;

import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.family.presentation.dto.GetFamilyResponse;
import com.sumheart.domain.user.domain.Users;
import com.sumheart.domain.user.domain.repository.UserRepository;
import com.sumheart.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryUserService {

  private final UserRepository userRepository;

  public Users getOne(Long id) {
    return userRepository.findById(id)
        .orElseThrow(UserNotFoundException::new);
  }

  public List<Users> findByFamily(Family family) {
    return userRepository.findByFamily(family);
  }

  public GetFamilyResponse getFamily(Long userId) {
    Users user = getOne(userId);
    Family family = user.getFamily();
    List<Users> users = findByFamily(family);
    return GetFamilyResponse.of(family, users, user);
  }
}
