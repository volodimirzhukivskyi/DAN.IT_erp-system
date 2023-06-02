package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.Manager;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
  List<Manager> findByDeletedFalse();
  Optional<Manager> findByIdAndDeletedFalse(Long userId);


}
