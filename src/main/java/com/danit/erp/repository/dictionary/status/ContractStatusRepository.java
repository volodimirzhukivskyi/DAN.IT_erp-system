package com.danit.erp.repository.dictionary.status;

import com.danit.erp.domain.dictionary.status.ContractStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractStatusRepository extends JpaRepository<ContractStatus,Byte> {
  Optional<ContractStatus> findByStatus(String status);
  Page<ContractStatus> findByDeletedFalse(Pageable pageable);
  List<ContractStatus> findByDeletedFalse();
  Optional<ContractStatus> findByIdAndDeletedFalse(Byte userId);
}
