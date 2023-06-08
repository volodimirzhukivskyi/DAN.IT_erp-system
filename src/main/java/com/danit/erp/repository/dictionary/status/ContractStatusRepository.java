package com.danit.erp.repository.dictionary.status;

import com.danit.erp.domain.dictionary.status.ContractStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractStatusRepository extends JpaRepository<ContractStatus,Long> {
  Optional<ContractStatus> findByStatus(String status);
  List<ContractStatus> findByDeletedFalse();
  Optional<ContractStatus> findByIdAndDeletedFalse(Long userId);
}
