package com.sumheart.domain.family.domain.repository;

import com.sumheart.domain.family.domain.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family,Long> {

  Optional<Family> findIdByInvitationCode(String invitationCode);
}
