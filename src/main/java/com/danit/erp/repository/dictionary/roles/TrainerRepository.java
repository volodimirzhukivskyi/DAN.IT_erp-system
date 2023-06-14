package com.danit.erp.repository.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Trainer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
  Optional<Trainer> findByFullName(String fullName);
  Page<Trainer> findByDeletedFalse(Pageable pageable);

  List<Trainer> findByDeletedFalse();

  Optional<Trainer> findByIdAndDeletedFalse(Long userId);


}
