package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.Email;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
  Optional<Email> findByIdCode(String idCode);

  Page<Email> findAll(Pageable pageable);

  Boolean existsByEmail(Email email);

  Optional<Email> findByEmail(String email);
}
