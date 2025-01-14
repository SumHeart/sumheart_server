package com.sumheart.domain.user.domain.repository;

import com.sumheart.domain.family.domain.Family;
import com.sumheart.domain.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

  Users findByEmail(String email);
  List<Users> findByFamily(Family family);
}
