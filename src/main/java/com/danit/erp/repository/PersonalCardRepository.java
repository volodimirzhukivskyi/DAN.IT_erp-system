package com.danit.erp.repository;

import com.danit.erp.domain.personalcard.PersonalCard;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalCardRepository extends JpaRepository<PersonalCard, Long> {
 Optional<PersonalCard> findByIdCode(String idCode);
}
