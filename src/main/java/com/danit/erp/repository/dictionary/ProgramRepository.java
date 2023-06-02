package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.Program;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
  Optional<Program> findByProgram(String nameProgram);

  List<Program> findByDeletedFalse();
  Optional<Program> findByIdAndDeletedFalse(Long userId);


}
