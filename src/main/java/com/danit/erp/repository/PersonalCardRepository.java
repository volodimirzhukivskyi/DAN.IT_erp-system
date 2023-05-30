package com.danit.erp.repository;

import com.danit.erp.domain.personalcard.PersonalCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalCardRepository extends JpaRepository<PersonalCard,Long> {
}
