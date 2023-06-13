package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.Program;
import com.danit.erp.domain.dictionary.Sessions;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionsRepository extends JpaRepository<Sessions,Long> {
  List<Sessions> findByDeletedFalse();
  Optional<Sessions> findByIdAndDeletedFalse(Long userId);

  Optional<Sessions>  findByProgram(Program program);
}
