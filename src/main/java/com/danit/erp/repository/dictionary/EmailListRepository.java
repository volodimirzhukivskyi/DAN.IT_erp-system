package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.Email;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailListRepository extends JpaRepository<Email,Long> {
  Optional<Email> findByIdCode(String idCode);


}
