package com.danit.erp.repository.card;

import com.danit.erp.domain.card.personal_card.PersonalCard;
import com.danit.erp.domain.dictionary.Email;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalCardRepository extends JpaRepository<PersonalCard, Long> {
  Optional<PersonalCard> findByIdCode(String idCode);
  Optional<PersonalCard> findByEmail_Email( String email);
}
