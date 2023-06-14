package com.danit.erp.repository.dictionary.status;

import com.danit.erp.domain.dictionary.status.SessionsStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionsStatusRepository extends JpaRepository<SessionsStatus,Long> {
  Optional<SessionsStatus> findByStatus(String status);
  Page<SessionsStatus> findByDeletedFalse(Pageable pageable);
  List<SessionsStatus> findByDeletedFalse();
  Optional<SessionsStatus> findByIdAndDeletedFalse(Long userId);
}
