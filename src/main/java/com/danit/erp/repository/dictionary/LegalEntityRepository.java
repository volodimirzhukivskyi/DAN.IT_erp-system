package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.LegalEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LegalEntityRepository extends JpaRepository<LegalEntity, Long> {
  Optional<LegalEntity> findByIdCode(String userId);

  List<LegalEntity> findByDeletedFalse();
  Optional<LegalEntity> findByIdAndDeletedFalse(Long userId);

}
