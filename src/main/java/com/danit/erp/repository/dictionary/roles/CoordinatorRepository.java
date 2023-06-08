package com.danit.erp.repository.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Coordinator;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {
  Optional<Coordinator> findByFullName(String fullName);

  List<Coordinator> findByDeletedFalse();
  Optional<Coordinator> findByIdAndDeletedFalse(Long userId);


}
