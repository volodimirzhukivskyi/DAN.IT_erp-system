package com.danit.erp.repository.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Mentor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
  Optional<Mentor> findByFullName(String fullName);
  Page<Mentor> findByDeletedFalse(Pageable pageable);
  List<Mentor> findByDeletedFalse();

  Optional<Mentor> findByIdAndDeletedFalse(Long userId);


}
