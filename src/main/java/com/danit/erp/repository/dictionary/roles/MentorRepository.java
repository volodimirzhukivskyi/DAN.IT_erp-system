package com.danit.erp.repository.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Mentor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
  Optional<Mentor> findByFullName(String fullName);

  List<Mentor> findByDeletedFalse();

  Optional<Mentor> findByIdAndDeletedFalse(Long userId);


}
