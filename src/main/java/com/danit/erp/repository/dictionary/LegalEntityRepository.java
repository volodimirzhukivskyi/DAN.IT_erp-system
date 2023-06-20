package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.LegalEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalEntityRepository extends JpaRepository<LegalEntity, Long> {
  Optional<LegalEntity> findByIdCode(String userId);

  Page<LegalEntity> findByDeletedFalse(Pageable pageable);

  List<LegalEntity> findByDeletedFalse();

  Optional<LegalEntity> findByIdAndDeletedFalse(Long userId);

}
