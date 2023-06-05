package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.ContractStatus;
import com.danit.erp.domain.dictionary.Group;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractStatusRepository extends JpaRepository<ContractStatus,Long> {
  Optional<ContractStatus> findByStatus(String status);
  List<ContractStatus> findByDeletedFalse();
  Optional<ContractStatus> findByIdAndDeletedFalse(Long userId);
}
