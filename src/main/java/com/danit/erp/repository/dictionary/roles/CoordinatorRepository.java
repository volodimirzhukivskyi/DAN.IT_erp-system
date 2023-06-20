package com.danit.erp.repository.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Coordinator;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {
  Optional<Coordinator> findByFullName(String fullName);
  Page<Coordinator> findByDeletedFalse(Pageable pageable);

  List<Coordinator> findByDeletedFalse();
  Optional<Coordinator> findByIdAndDeletedFalse(Long userId);


}
