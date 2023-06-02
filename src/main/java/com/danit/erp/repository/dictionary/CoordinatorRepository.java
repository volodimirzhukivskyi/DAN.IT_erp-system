package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.Coordinator;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {
  List<Coordinator> findByDeletedFalse();
  Optional<Coordinator> findByIdAndDeletedFalse(Long userId);


}
