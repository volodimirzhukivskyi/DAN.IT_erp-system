package com.danit.erp.repository.dictionary.status;

import com.danit.erp.domain.dictionary.status.GroupStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupStatusRepository extends JpaRepository<GroupStatus,Long> {
  Optional<GroupStatus> findByStatus(String status);
  List<GroupStatus> findByDeletedFalse();
  Optional<GroupStatus> findByIdAndDeletedFalse(Long userId);
}
