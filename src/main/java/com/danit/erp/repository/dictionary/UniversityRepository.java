package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.University;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
  List<University> findByDeletedFalse();

  Optional<University> findByIdAndDeletedFalse(Long userId);

  Page<University> findByDeletedFalse(Pageable pageable);

  Optional<University> findByName(String name);
}
