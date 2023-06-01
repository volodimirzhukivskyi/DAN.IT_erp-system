package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.University;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
  List<University> findByDeletedFalse();
  Optional<University> findByIdAndDeletedFalse(Long userId);

  Optional<University>  findByName(String name);
}
