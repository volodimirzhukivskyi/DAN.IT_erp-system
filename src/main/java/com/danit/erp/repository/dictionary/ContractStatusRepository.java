package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.ContractStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractStatusRepository extends JpaRepository<ContractStatus,Long> {
  List<ContractStatus> findByDeletedFalse();
  Optional<ContractStatus> findByIdAndDeletedFalse(Long userId);
}
