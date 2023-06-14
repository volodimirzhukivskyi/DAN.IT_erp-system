package com.danit.erp.repository.dictionary.status;

import com.danit.erp.domain.dictionary.status.GroupStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupStatusRepository extends JpaRepository<GroupStatus,Long> {
  Optional<GroupStatus> findByStatus(String status);
  Page<GroupStatus> findByDeletedFalse(Pageable pageable);
  List<GroupStatus> findByDeletedFalse();
  Optional<GroupStatus> findByIdAndDeletedFalse(Long userId);
}
