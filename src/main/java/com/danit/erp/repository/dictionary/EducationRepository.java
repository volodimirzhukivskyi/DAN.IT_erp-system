package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.Education;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
  Optional<Education>findBySpecialization(String name);
  List<Education> findByDeletedFalse();
  Page<Education> findByDeletedFalse(Pageable pageable);
  Optional<Education> findByIdAndDeletedFalse(Long userId);


}
