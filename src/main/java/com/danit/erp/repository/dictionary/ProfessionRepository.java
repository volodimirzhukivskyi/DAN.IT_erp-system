package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.Profession;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
  List<Profession> findByDeletedFalse();
  Optional<Profession> findByIdAndDeletedFalse(Long userId);

  Optional<Profession>  findByName(String name);
}
