package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.Education;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
  List<Education> findByDeletedFalse();
  Optional<Education> findByIdAndDeletedFalse(Long userId);


}
